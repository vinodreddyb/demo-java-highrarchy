package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;


class DemoApplicationTests {

	public static void main(String[] args) throws IOException {


		var mapper = new ObjectMapper();
		var file = new File("C:\\Users\\Vinod\\test\\demo\\test2.json");
		var val = mapper.readValue(file, new TypeReference<List<EilCategories>>() {
		});

		var cmap = val.stream().collect(Collectors.toMap(EilCategories::getId, d-> d));

		List<String> visitedNode = new ArrayList<>();
		for(var d : val) {

			if(visitedNode.contains(d.getId())) {
				continue;
			}
			var child = new ArrayList<Data>();
			var eilSheet = new EilSheetDto();
			addChildren1(d, cmap,visitedNode);
			eilSheet= eilSheetDto1(d);
			System.out.println(d);
		}



	}

	static void addChildren1(EilCategories parent, Map<String,EilCategories> dataMap,List<String> visitedNode) {
		if(null != parent.getChildren()) {
			for(var child : parent.getChildren()) {

				visitedNode.add(child);
				var e = dataMap.get(child);
				parent.getChild().add(e);

				addChildren1(e,dataMap,visitedNode);
			}
		}
	}
	static void addChildren(Data parent, Map<String,Data> dataMap,List<String> visitedNode) {
		if(null != parent.getChildren()) {
			for(var child : parent.getChildren()) {

				visitedNode.add(child);
				Data e = dataMap.get(child);
				parent.getChild().add(e);

				addChildren(e,dataMap,visitedNode);
			}
		}
	}

	private static EilSheetDto  eilSheetDto(Data d) {

		var eilcat = new EilCategoryDTO();
		eilcat.setName(d.getName());
		eilcat.setWeightage(d.getWeightage());
		var eil = new EilSheetDto();
		eil.setData(eilcat);
		eil.setKey(d.getId().replace(".","-"));
		for(var c : d.getChild()) {
			if(null == d.getChildren() || d.getChildren().isEmpty()) {
				break;
			}
			eil.getChildren().add(eilSheetDto(c));
		}
		return eil;
	}
	private static EilSheetDto  eilSheetDto1(EilCategories d) {

		var eilcat = new EilCategoryDTO();
		eilcat.setName(d.getName());
		eilcat.setWeightage(d.getWeightage());
		var eil = new EilSheetDto();
		eil.setData(eilcat);
		eil.setKey(d.getId().replace(".","-"));
		for(var c : d.getChild()) {
			if(null == d.getChildren() || d.getChildren().isEmpty()) {
				break;
			}
			eil.getChildren().add(eilSheetDto1(c));
		}
		return eil;
	}

}
