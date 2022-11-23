package jrails;

public class Html {

    private String code;

    public String toString() {
        return code;
    }

    public Html seq(Html h) {
        this.code += h.code;

        return this;
    }

    public Html br() {
        Html html = new Html();
        html.code = "<br/>";

        return html;
    }

    public Html t(Object o) {
        // Use o.toString() to get the text for this
        Html html = new Html();
        html.code = o.toString();

        return html;
    }

    public Html p(Html child) {
        Html html = new Html();
        html.code = "<p>" + child.code + "</p>";

        return html;
    }

    public Html div(Html child) {
        Html html = new Html();
        html.code = "<div>" + child.code + "</div>";

        return html;
    }

    public Html strong(Html child) {
        Html html = new Html();
        html.code = "<strong>" + child.code + "</strong>";

        return html;
    }

    public Html h1(Html child) {
        Html html = new Html();
        html.code = "<h1>" + child.code + "</h1>";

        return html;
    }

    public Html tr(Html child) {
        Html html = new Html();
        html.code = "<tr>" + child.code + "</tr>";

        return html;
    }

    public Html th(Html child) {
        Html html = new Html();
        html.code = "<th>" + child.code + "</th>";

        return html;
    }

    public Html td(Html child) {
        Html html = new Html();
        html.code = "<td>" + child.code + "</td>";

        return html;
    }

    public Html table(Html child) {
        Html html = new Html();
        html.code = "<table>" + child.code + "</table>";

        return html;
    }

    public Html thead(Html child) {
        Html html = new Html();
        html.code = "<thead>" + child.code + "</thead>";

        return html;
    }

    public Html tbody(Html child) {
        Html html = new Html();
        html.code = "<tbody>" + child.code + "</tbody>";

        return html;
    }

    public Html textarea(String name, Html child) {
        Html html = new Html();
        html.code = "<a name=\"" + name + "\">" + child.code + "</a>";

        return html;
    }

    public Html link_to(String text, String url) {
        Html html = new Html();
        html.code = "<a href=\"" + url + "\">" + text + "</a>";

        return html;
    }

    public Html form(String action, Html child) {
        Html html = new Html();
        html.code = "<form action=\"" + action + "\">" + child.code + "</form>";

        return html;
    }

    public Html submit(String value) {
        Html html = new Html();
        html.code = "<input type=\"submit\" value=\"" + value + "\">";

        return html;
    }
}