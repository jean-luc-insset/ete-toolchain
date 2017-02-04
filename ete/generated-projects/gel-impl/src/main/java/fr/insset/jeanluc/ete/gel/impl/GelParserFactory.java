package fr.insset.jeanluc.ete.gel.impl;

import fr.insset.jeanluc.gel.GelLexer;
import fr.insset.jeanluc.gel.GelParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author jldeleage
 */
public class GelParserFactory {
    
    protected static GelParser newParser(String inExpression) {
        GelLexer lexer = null;
        ANTLRInputStream input = new ANTLRInputStream(inExpression);
        lexer = new GelLexer(input);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        GelParser parser = new GelParser(commonTokenStream);
        return parser;
    }

}


