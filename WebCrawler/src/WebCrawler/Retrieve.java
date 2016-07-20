
package csc302;
import java.util.List;

public class Retrieve 
{
     private ResponseData responseData;
   private final java.util.LinkedList<Integer> queue =
           new java.util.LinkedList<>();
   public synchronized ResponseData getResponseData() {
       return responseData;
   }
	public void setResponseData(ResponseData responseData){
            this.responseData 
                = responseData;
        }
   @Override
	public String toString() { return "ResponseData[" + responseData + "]";}

	class ResponseData {
	    private List<Result> results;
	    public synchronized List<Result> getResults() {return results;}
	    public void setResults(List<Result> results)
            {this.results = results;}
            @Override
	    public String toString() {return "Results[" + results + "]";}
        }
        class Result {
	    private String url;
	    private String title;
	    public synchronized String  getUrl(){return url;}
	    public synchronized String getTitle() {return title;}
	    public  void setUrl(String url){this.url = url;}
	    public void setTitle(String title){this.title = title;}
            @Override
	    public String toString() { return "Result[url:" + url +
                    ",title:" + title + "]"; }
  
        }}

