package fitnesse.wikitext.test;

import fitnesse.wiki.WikiPage;
import fitnesse.wikitext.parser.SymbolType;
import org.junit.Test;


public class LinkTest {
    @Test
    public void scansLinks() {
        ParserTest.assertScansTokenType("http://mysite.org", SymbolType.Link, true);
        ParserTest.assertScansTokenType("https://mysite.org", SymbolType.Link, true);
        ParserTest.assertScansTokenType("http:/mysite.org", SymbolType.Link, false);
        ParserTest.assertScansTokenType("httpx://mysite.org", SymbolType.Link, false);
    }

    @Test public void parsesLinks() throws Exception {
        ParserTest.assertParses("http://mysite.org", "SymbolList[Link[SymbolList[Text]]]");
    }

    @Test public void translatesLinks() {
        ParserTest.assertTranslatesTo("http://mysite.org","<a href=\"http://mysite.org\">http://mysite.org</a>");
        ParserTest.assertTranslatesTo("http://files/myfile","<a href=\"/files/myfile\">http://files/myfile</a>");
    }

    @Test public void translatesLinkWithVariable() {
        ParserTest.assertTranslatesTo("http://${site}", new TestVariableSource("site", "mysite.org"), "<a href=\"http://mysite.org\">http://mysite.org</a>");
    }
}
