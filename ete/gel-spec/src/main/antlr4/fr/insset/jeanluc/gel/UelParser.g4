grammar UelParser;

import GelParser;


options {
    tokenVocab = UelLexer;
}



qvtExpression :
    GERONIMO
    gelExpression
    GERONIMO
;