package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * A web browser that keeps track of the user's browsing history
 *
 * @author Shawn Zhang Joshua White
 * @version January 29, 2024
 */

public class WebBrowser {
    //Stacks to keep track of the user's browsing history
    private final Stack<URL> backButtonStack;
    private final Stack<URL> forwardButtonStack;
    private URL currentPage;

    /**
     * Construct a WebBrowser with no history.
     */
    public WebBrowser(){
        backButtonStack = new LinkedListStack<>();
        forwardButtonStack = new LinkedListStack<>();
        currentPage = null;
    }

    /**
     * Construct a WebBrowser with a given url history.
     * The urls should be in order from oldest to newest.
     */
    public WebBrowser(SinglyLinkedList<URL> history){
        backButtonStack = new LinkedListStack<>();
        forwardButtonStack = new LinkedListStack<>();

        Stack<URL> temp = new LinkedListStack<>();

        for(var url : history){
            temp.push(url);
        }

        currentPage = temp.pop();

        while (!temp.isEmpty()){
            backButtonStack.push(temp.pop());
        }
    }

    /**
     * Visits a webpage
     * @param webpage - the webpage to visit
     */
    public void visit(URL webpage){
        forwardButtonStack.clear();
        if(currentPage != null)
            backButtonStack.push(currentPage);
        currentPage = webpage;
    }

    /**
     * Goes back to the previous page
     * @return the previous page
     * @throws NoSuchElementException if there is no previous page
     */
    public URL back() throws NoSuchElementException {
        forwardButtonStack.push(currentPage);
        return currentPage = backButtonStack.pop();
    }

    /**
     * Goes forward to the next page
     * @return the next page
     * @throws NoSuchElementException if there is no next page
     */
    public URL forward() throws NoSuchElementException{
        backButtonStack.push(currentPage);
        return currentPage = forwardButtonStack.pop();
    }

    /**
     * Get the url history in this browser, in chronological order
     * @return A SinglyLinkedList of urls sorted from oldest to newest
     */
    public SinglyLinkedList<URL> history(){
        SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();
        Stack<URL> temp = new LinkedListStack<URL>();

        while(!backButtonStack.isEmpty()){
            URL url = backButtonStack.pop();
            temp.push(url);
        }

        if (currentPage != null) {
            history.insertFirst(currentPage);
        }

        while(!temp.isEmpty()){
            URL url = temp.pop();
            history.insertFirst(url);
        }

        return history;
    }
}
