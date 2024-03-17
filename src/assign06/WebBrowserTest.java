package assign06;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {

    @Test
    void visit() throws MalformedURLException {
        URL page = new URL("https://www.google.com");

        WebBrowser browser = new WebBrowser();
        browser.visit(page);
        assertEquals(browser.history().getFirst().toString(), page.toString());
    }

    @Test
    void back() throws MalformedURLException {
        URL page1 = new URL("https://www.google.com");
        URL page2 = new URL("https://www.bing.com");

        WebBrowser browser = new WebBrowser();
        browser.visit(page1);
        browser.visit(page2);


        assertEquals(browser.back().toString(), page1.toString());
    }

    @Test
    void forward() throws MalformedURLException {
        URL page1 = new URL("https://www.google.com");
        URL page2 = new URL("https://www.bing.com");

        WebBrowser browser = new WebBrowser();
        browser.visit(page1);
        browser.visit(page2);
        browser.back();

        assertEquals(browser.forward().toString(), page2.toString());
    }

    @Test
    void history() throws MalformedURLException {
        URL page = new URL("https://www.google.com");
        SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();
        history.insertFirst(page);

        WebBrowser browser = new WebBrowser(history);

        assertEquals(browser.history().getFirst().toString(), page.toString());
    }

    @Test
    void backThrowsExceptionWhenNoPreviousPage() {
        WebBrowser browser = new WebBrowser();
        assertThrows(NoSuchElementException.class, browser::back);
    }

    @Test
    void forwardThrowsExceptionWhenNoNextPage() {
        WebBrowser browser = new WebBrowser();
        assertThrows(NoSuchElementException.class, browser::forward);
    }

    @Test
    void historyReturnsCorrectHistory() throws MalformedURLException {
        URL page1 = new URL("https://www.google.com");
        URL page2 = new URL("https://www.bing.com");
        URL page3 = new URL("https://www.github.com");

        WebBrowser browser = new WebBrowser();
        browser.visit(page1);
        browser.visit(page2);
        browser.back();
        browser.visit(page3);

        SinglyLinkedList<URL> expectedHistory = new SinglyLinkedList<>();
        expectedHistory.insertFirst(page3);
        expectedHistory.insertFirst(page1);

        assertEquals(expectedHistory.getFirst().toString(), browser.history().getFirst().toString());
    }
}