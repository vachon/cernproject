package json;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.json.simple.JSONObject;

import cern.mpe.systems.core.domain.relation.SystemRelation;

public class JSONExporter {
	
	
	public void generateJson(Collection<SystemRelation> relations){
		
		
		LinkList<SystemRelation> list = new LinkList();
		
		for(SystemRelation rel : relations)
		{
			list.insert(rel,0);
		}
		
		System.out.println(list);
		try (FileWriter file = new FileWriter("C:/Users/user/Desktop/sysboard.json")) {
			file.write("");
			System.out.println("Successfully generate JSON File...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String setProp(String prop, String value){
		return prop + ": \"" + value +"\"";
	}
}
