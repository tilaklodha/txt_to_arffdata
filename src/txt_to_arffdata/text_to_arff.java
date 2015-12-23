package txt_to_arffdata;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;



public class text_to_arff{
	
	static HashMap<String, Integer> mapinterrogative = new HashMap<String, Integer>();
	static HashMap<String, Integer> mapimperative = new HashMap<String, Integer>();
	static HashMap<String, Integer> mapdeclarative = new HashMap<String, Integer>();
	static HashMap<String, Integer> mapmerge = new HashMap<String, Integer>();
    
    int attribnum=1; // number of attributes
	
	Scanner scanner=new Scanner(System.in);
	
	
	
		public static void main(String[] args) throws IOException {
				        
	        
			text_to_arff object= new text_to_arff();
			
			mapinterrogative= object.interrogative();
			mapimperative= object.imperative();
			mapdeclarative= object.declarative();
			mapmerge=object.attributefilegenerate();
			
			System.out.println(mapmerge);
			System.out.println(mapmerge.size());
	           
		}	
		
		
		
		
		
		
		
		
		
		

		
		
		public HashMap<String,Integer> interrogative() throws FileNotFoundException{
			
			//--------Interrogative attributes into HashMap	        
			scanner = new Scanner(new FileReader("./src/resources/new0.txt")); // file input for interrogative sentences
		
	        

	        while (scanner.hasNextLine()) {
	        	String sentence= scanner.nextLine();
	        
	        	sentence.replaceAll("[-+.^:,:'/]", "");	        	
	            String[] columns = sentence.split("((?<=;)|(?=;))| |((?<=\\?)|(?=\\?))|((?<=//.)|(?=//.))");
	            int numberofwords= columns.length;
	            for(int i=0;i<numberofwords;i++){
	            	if(mapinterrogative.containsKey(columns[i])){
	            		
	            	}
	            	else{
	            		mapinterrogative.put(columns[i], attribnum);
	            		attribnum++;
	            	}
	            }
	            
	            
	            
	        }
	        
	        scanner.close();
	//--------end of Interrogative Sentences
			return mapinterrogative;
			
			
			
			
		}
		
		
		public HashMap<String,Integer> imperative() throws FileNotFoundException{
			//--------Imperative attributes into HashMap		        
	        scanner = new Scanner(new FileReader("./src/resources/new1.txt"));			// file input for imperative sentences
	        
	        attribnum=1;
	        while (scanner.hasNextLine()) {
	        	String sentence1= scanner. nextLine();
	        	sentence1.replaceAll("[-+.^:,:'/]", "");	        	
	            String[] columns = sentence1.split("((?<=;)|(?=;))| |((?<=\\?)|(?=\\?))");
	            int numberofwords= columns.length;
	            for(int i=0;i<numberofwords;i++){
	            	if(mapimperative.containsKey(columns[i])){
	            		
	            	}
	            	else{
	            		mapimperative.put(columns[i], attribnum);
	            		attribnum++;
	            	}
	            }
	            
	        }
	             
	        scanner.close();
//----------- End of Imperative Sentences
			return mapimperative;
			
			
			
		}
		
		
		
		
		
		
		public HashMap<String,Integer> declarative() throws FileNotFoundException{
			//--------Daclarative attributes into HashMap		        
	        scanner = new Scanner(new FileReader("./src/resources/new2.txt"));			// file input for declarative sentences
	        
	        attribnum=1;
	        while (scanner.hasNextLine()) {
	        	String sentence2= scanner. nextLine();
	        	sentence2.replaceAll("[-+.^:,:'/]", "");	        	
	            String[] columns = sentence2.split("((?<=;)|(?=;))| |((?<=\\?)|(?=\\?))");
	            int numberofwords= columns.length;
	            for(int i=0;i<numberofwords;i++){
	            	if(mapdeclarative.containsKey(columns[i])){
	            		
	            	}
	            	else{
	            		mapdeclarative.put(columns[i], attribnum);
	            		attribnum++;
	            	}
	            }
	            
	        }
	             
	        scanner.close();
			return mapdeclarative;
			
			
			

		}
		
		
		public HashMap<String,Integer> attributefilegenerate() throws IOException{
			
			//----------Merging of Different Attributes in one HashMap	        
	        mapmerge.putAll(mapinterrogative);
	        mapmerge.putAll(mapimperative);
	        mapmerge.putAll(mapdeclarative);
	        
	        //System.out.println(mapmerge);
	        //System.out.println(mapmerge.keySet());
	        //System.out.println(mapmerge.size());  		// Size of attributes
	        
	        FileWriter fstream;
	        BufferedWriter out;
	        // then, define how many records we want to print to the file
	        int recordsToPrint = mapmerge.size();
	        // create your filewriter and bufferedreader
	        fstream = new FileWriter("./src/resources/attributes.arff");  // Output file of Attributes
	        out = new BufferedWriter(fstream);

	        // initialize the record count
	        int count = 0;

	        // create your iterator for your map
	        Iterator<Entry<String, Integer>> it = mapmerge.entrySet().iterator();

	        // then use the iterator to loop through the map, stopping when we reach the
	        // last record in the map or when we have printed enough records
	        while (it.hasNext() && count < recordsToPrint) {

	            // the key/value pair is stored here in pairs
	            Map.Entry<String, Integer> pairs = it.next();
	            //System.out.println("Key is " + pairs.getKey());

	            // since you only want the value, we only care about pairs.getValue(), which is written to out
	            if(pairs.getKey()!=" "){
	            	out.write(pairs.getKey() + " " + "\n");
	            }

	            // increment the record count once we have printed to the file
	            count++;
	        }
	        // lastly, close the file and end
	        out.close();
			
			return mapmerge;
			
		}
}

