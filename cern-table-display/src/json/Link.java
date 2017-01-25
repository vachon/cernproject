package json;

public class Link {
    private String target;
    private String source;
    private String type;
    
    public Link() {
            
            // TODO Auto-generated constructor stub
    }

    public String getTarget() {
            return target;
    }

    public void setTarget(String target) {
            this.target = target;
    }

    public String getSource() {
            return source;
    }

    public void setSource(String source) {
            this.source = source;
    }

    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }

    public Link(String target, String source, String type) {
            super();
            this.target = target;
            this.source = source;
            this.type = type;
    }

    @Override
    public String toString() {
            return "\t{source: \"" + source + "\" , target: \"" + target + "\", type: \""+ type + "\"}"  ;
    }
}
