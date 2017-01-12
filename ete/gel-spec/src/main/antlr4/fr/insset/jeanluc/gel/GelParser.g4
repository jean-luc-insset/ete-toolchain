parser grammar GelParser;



options {
    tokenVocab = GelLexer;
}



//============================================================================//
// This parser is the basis for OCL parser, UEL parser and MOF-QVTo parser    //
// It handles operator priority.                                              //
// Any parser which imports this one can override any rule to customize the   //
// parser to its own needs.                                                   //
//============================================================================//





//============================================================================//
//                             E N T R Y P O I N T                            //
//============================================================================//


gelExpression :
    xorExpression
;


//============================================================================//
//                     L O G I C A L   O P E R A T I O N S                    //
//============================================================================//


xorExpression :
    (
    orExpression
    XOR
    )*
    orExpression
;


orExpression :
    (
    andExpression
    OR
    )*
    andExpression
;


andExpression :
    (
    orderExpression
    AND
    )*
    orderExpression
;


//============================================================================//


// In this grammar, comparisons are not associative. One cannot write expressions
// such that
//      a > b > c
orderExpression :
    (
        compareExpression
        orderOperator
    )?
    compareExpression
;


orderOperator :
    GT
    |
    GE
    |
    LT
    |
    LE
;


// In this grammar, = and <> are not associative : one cannot write expressions
// such that
//      a = b = c
compareExpression :
    (
        addOrSubExpression
        compareOperator
    )?
    addOrSubExpression
;


compareOperator :
    EQUAL
    |
    NOTEQUAL
;


//============================================================================//
//                  A R I T H M E T I C   O P E R A T I O N S                 //
//============================================================================//



addOrSubExpression :
    (
        multOrDivExpression
        addOrSubOperator
    )*
    multOrDivExpression
;



addOrSubOperator :
    ADD
    |
    SUB
;


multOrDivExpression :
    (
        navExpression
        mulOperator
    )*
    
    navExpression
;


mulOperator :
    MUL
    |
    DIV
    |
    MOD
;



//============================================================================//
//                             N A V I G A T I O N                            //
//============================================================================//


navExpression :
    (
    stepExpression
    navOperator
    )*
    stepExpression
;


navOperator :
    DOT | ARROW
;


stepExpression :
    atomicExpression
    (
    filterExpression
    )*
;


filterExpression :
    LBRACK
    gelExpression
    RBRACK
;


//============================================================================//
//                               L I T E R A L S                              //
//============================================================================//


atomicExpression :
    identifier
    |
    literal;

literal :
    integerLiteral
    |
    floatingPointLiteral
    |
    booleanLiteral
    |
    dateLiteral
    |
    characterLiteral
    |
    stringLiteral
    |
    nullLiteral
;




identifier           : Identifier;
integerLiteral       : IntegerLiteral;
floatingPointLiteral : FloatingPointLiteral;
booleanLiteral       : BooleanLiteral;
dateLiteral          : DateLiteral;
characterLiteral     : CharacterLiteral;
stringLiteral        : StringLiteral;
nullLiteral          : NullLiteral;


