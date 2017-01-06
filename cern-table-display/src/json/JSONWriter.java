package json;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import cern.mpe.systems.core.domain.relation.SystemRelation; 
 
public class JSONWriter {
	
	public static void genJSON(Collection<SystemRelation> relations) throws IOException{
		
		JSONArray jSon = new JSONArray();
		
		for(SystemRelation rel : relations)
		{
			JSONObject obj = new JSONObject();
			obj.put("source", rel.getSource().getName());
			obj.put("target", rel.getTarget().getName());
			obj.put("type", "suit");
			jSon.add(obj);
			
		}
		
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("C:/Users/user/Desktop/json.json")) {
			file.write(jSon.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + jSon);
		}
		
	}
}
/*[
{source: "sysboard", target: "paul", type: "suit"},
{source: "sysboard", target: "nicolas", type: "suit"},
{source: "nicolas", target: "biere", type: "licensing"},
{source: "biere", target: "sysboard", type: "resolved"},
{source: "biere", target: "nicolas", type: "resolved"},
{source: "biere", target: "luc", type: "drink"}
]
*/