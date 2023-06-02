package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.domain.TaskList;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SetOperation;
import org.springframework.data.mongodb.core.aggregation.ScriptOperators.Function;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void testQuery() {
        MatchOperation match = Aggregation.match(Criteria.where("incident_id").in("2111240156000494"));
        String body = "function(updates) "+
                         "{ updates.sort((a, b) => new Date(b.dispatchInfo.dispatchCreatedDateTime) - new Date(a.dispatchInfo.dispatchCreatedDateTime)); " +
                            "return updates[0];}";
        Function func = Function.function(body).args("$dispatches").lang("js");
        SetOperation setOp = SetOperation.set("dispatches").toValue(func);

        Aggregation newAggregation = Aggregation.newAggregation(match, setOp);
        System.out.println(newAggregation);

        // Instead of Document you can keep your pojo here
        AggregationResults<Document> aggregate = mongoTemplate.aggregate(newAggregation, "teset", Document.class);

        aggregate.forEach(s -> {
            System.out.println(s);
        });
        System.out.println("Finished");
    }

    public void saveTask() {
        Task task = new Task();
        task.setTaskId(1);
        task.setTaskName("Frst task");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TaskList taskList = new TaskList();
        taskList.setTasks(tasks);
        taskList.setName("Sample Task");
        mongoTemplate.save(taskList,"task_lists");

    }



    public void updateTask() {
        Task task = new Task();
        task.setTaskId(3);
        task.setTaskName("third task");
        Update update = new Update();
        update.addToSet("tasks", task);

        Query query = new Query().addCriteria(Criteria.where("name").is("Sample Task"));
        mongoTemplate.findAndModify(query, update, TaskList.class,"task_lists");
    }

    public void updateTask1() {
        Update update = new Update();
        update.set("name", "third task test1");
        Query query = new Query().addCriteria(Criteria.where("gender").is("Male"));
        //List<TaskList> one = mongoTemplate.findAll( TaskList.class);
        //UpdateResult updateResult = mongoTemplate.updateFirst(query, update, TaskList.class);
       // System.out.println(updateResult);
        mongoTemplate.upsert(query,update,TaskList.class);
    }
}
