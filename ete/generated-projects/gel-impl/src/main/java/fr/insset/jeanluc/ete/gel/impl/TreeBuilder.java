package fr.insset.jeanluc.ete.gel.impl;


import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.gel.GelParser;
import fr.insset.jeanluc.gel.GelParserBaseVisitor;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ParserRuleContext;




public class TreeBuilder extends GelParserBaseVisitor<GelExpression> {


    protected GelExpression buildBinaryExpression(ParserRuleContext ctx) throws EteException {
        int     childCount = ctx.getChildCount();
        GelExpression   result = ctx.getChild(0).accept(this);
        // run through the operands
        for (int i=2 ; i<childCount ; i+=2) {
            GelExpression   child   = ctx.getChild(i).accept(this);
            String          operator = ctx.getChild(i-1).getText();
            GelExpression   exp;
            try {
                exp = (GelExpression)FactoryRegistry.newInstance(operator);
            } catch (InstantiationException ex) {
                Logger.getLogger(TreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
                throw new EteException(ex);
            }
            exp.addOperand(result);
            exp.addOperand(child);
            result = exp;
            // TODO : manage type
        }
        return result;
    }


    //========================================================================//


    public GelExpression visitOppExpressionCS(GelParser.OppExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitAndExpressionCS(GelParser.AndExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitXorExpressionCS(GelParser.XorExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
                public GelExpression visitEqualExpressionCS(GelParser.EqualExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
                public GelExpression visitDifferentExpressionCS(GelParser.DifferentExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitGreaterOrEqualExpressionCS(GelParser.GreaterOrEqualExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitVariableDeclarationExpressionCS(GelParser.VariableDeclarationExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
                        public GelExpression visitOrExpressionCS(GelParser.OrExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitLessOrEqualExpressionCS(GelParser.LessOrEqualExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitNotExpressionCS(GelParser.NotExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitDivExpressionCS(GelParser.DivExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitAddExpressionCS(GelParser.AddExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitMinusExpressionCS(GelParser.MinusExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitGreaterThanExpressionCS(GelParser.GreaterThanExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
                public GelExpression visitLambdaExpressionCS(GelParser.LambdaExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitMultExpressionCS(GelParser.MultExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
        public GelExpression visitLessThanExpressionCS(GelParser.LessThanExpressionContext ctx) throws EteException {
        return buildBinaryExpression(ctx);
    }
    

}

