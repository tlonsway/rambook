import java.util.*;
public class post
{
    private String image;
    private String postName;
    private String postTime;
    private ArrayList<String> comments = new ArrayList<String>();
    public post(String i, String time, String n )
    {
        image = i;
        postTime = time;
        postName = n;
    }
    public void addComment(String comment)
    {
        comments.add(comment);
    }
    public boolean removeComment(int i)
    {
        if(i >= comments.size())
            return false;
        else 
            comments.remove(i);
        return true;
    }
    public ArrayList<String> getComments()
    {
        return comments;
    }
    public String getImage()
    {
        return image;
    }
    public String getName()
    {
        return postName;
    }
    public String getTime()
    {
        return postTime;
    }
}