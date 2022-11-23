package jrails;
 
public class View {
    public static Html empty() {
         Html html = new Html();
         return html;
    }

    public static Html br() {
        Html html = new Html();
        return html.br();
    }

    public static Html t(Object o) {
        Html html = new Html();
        return html.t(o);
    }

    public static Html p(Html child) {
        Html html = new Html();
        return html.p(child);
    }

    public static Html div(Html child) {
        Html html = new Html();
        return html.div(child);
    }

    public static Html strong(Html child) {
        Html html = new Html();
        return html.strong(child);
    }

    public static Html h1(Html child) {
        Html html = new Html();
        return html.h1(child);
    }

    public static Html tr(Html child) {
        Html html = new Html();
        return html.tr(child);
    }

    public static Html th(Html child) {
        Html html = new Html();
        return html.th(child);
    }

    public static Html td(Html child) {
        Html html = new Html();
        return html.td(child);
    }

    public static Html table(Html child) {
        Html html = new Html();
        return html.table(child);
    }

    public static Html thead(Html child) {
        Html html = new Html();
        return html.thead(child);
    }

    public static Html tbody(Html child) {
        Html html = new Html();
        return html.tbody(child);
    }

    public static Html textarea(String name, Html child) {
        Html html = new Html();
        return html.textarea(name, child);
    }

    public static Html link_to(String text, String url) {
        Html html = new Html();
        return html.link_to(text, url);
    }

    public static Html form(String action, Html child) {
        Html html = new Html();
        return html.form(action, child);
    }

    public static Html submit(String value) {
        Html html = new Html();
        return html.submit(value);
    }
}
