//#SPLIT_SOURCE_TEST _____________________ WYSIWYG ALT string 
safd` asdf \x\f      /*sdf asd`asdf
safd` asdf \a\f\ NUL /*sdf asd`c
safd` asdf \\\f      /*sdf asd`w
safd` asdf \\\f      /*sdf asd`d
safd` asdf
 \\\f /*sdf asd`dfoobar
/+#LEXERTEST
ID,STRING_WYSIWYG,ID,EOL,
ID,STRING_WYSIWYG,EOL,
ID,STRING_WYSIWYG,EOL,
ID,STRING_WYSIWYG,EOL,
ID,STRING_WYSIWYG,ID,EOL,
+/

//#SPLIT_SOURCE_TEST _______________ 
foo` asdf \x\f /*
asdf
/+#LEXERTEST
ID,ERROR
+/

//#SPLIT_SOURCE_TEST _____________________ WYSIWYG string 
r" asfdd \f/*sdf asd"asdf
r" asdf \x\f\ NUL /*sdf asd"c
r" asdf \a\f /*sdf asd"w
r" asdf \\\f /*sdf asd"d
r" asdf
 \\\f /*sdf asd"dfoobar
xxr"asdf"rrr"asdf"
rambo rrrrr
/+#LEXERTEST
STRING_WYSIWYG,ID,EOL,
STRING_WYSIWYG,EOL,
STRING_WYSIWYG,EOL,
STRING_WYSIWYG,EOL,
STRING_WYSIWYG,ID,EOL,
ID,STRING_DQ,ID,STRING_DQ,EOL,
ID,WS,ID,EOL,
+/

//#SPLIT_SOURCE_TEST _______________ 
foor" asdf \x\f /*

/+#LEXERTEST
ID,ERROR
+/

//#SPLIT_SOURCE_TEST _____________________ DOUBLE QUOTED string 
saf" asfdd \f/*sdf asd"asdf
" asfdd \f/*sdf asd"c
" asfdd \f/*sdf asd"w
" asfdd \f/*sdf asd"d
R" asfdd \f/*sdf asd"D
/+#LEXERTEST
ID,STRING_DQ,ID,EOL,
STRING_DQ,EOL,
STRING_DQ,EOL,
STRING_DQ,EOL,
ID,STRING_DQ,ID,EOL,
+/

//#SPLIT_SOURCE_TEST _______________ DQ_STRING - error
foo" asdf /*

/+#LEXERTEST
ID,ERROR
+/

//#SPLIT_SOURCE_TEST ________________ DQ_STRING - escape sequences and weird characters
" \"asfd/*sdf asd"foobar
"  testing newline inside string

"c
"   \'  \"  \?  \\  \a  \b  \f  \n  \r  \t  \v  \ NUL
  \x0A\xF2
  \123\12\1xx
  \u00DA\uF1DAxx
  \U00DAF1DA\U123456768\UABCDEF012xxx
  \&quot;\&amp;\&lt;  // NamedCharacterEntity
    /*aaasasasassa "w
foobar
/+#LEXERTEST
STRING_DQ,ID,EOL,
STRING_DQ,EOL,
STRING_DQ,EOL,
ID,EOL
+/

//#SPLIT_SOURCE_TEST _______________ DQ_STRING - escape sequences (invalid)
foo"  \c" "  \z" "  \xZZ"
"  \u" "  \uFFA" "  \uFFAZ" "  \uZZ12"
"  \U" "  \UFFA" "  \U1234FFAZ" "  \u1234ZZ12"
"  \&quotxxx;\&xxxamp;\&xxlt;"
/+#LEXERTEST
ID,STRING_DQ,WS,STRING_DQ,WS,STRING_DQ,EOL,
STRING_DQ,WS,STRING_DQ,WS,STRING_DQ,WS,STRING_DQ,EOL,
STRING_DQ,WS,STRING_DQ,WS,STRING_DQ,WS,STRING_DQ,EOL,
STRING_DQ,EOL,
+/
// note: on the above we still expect string tokens and not errors  
// because escape sequences will be analized in semantic pass.


//#SPLIT_SOURCE_TEST _____________________ HEX string 
x"asfdd \f/*sdf asd"asdf
x" asfdd \f/*sdf asd"c
x" asfdd \f/*sdf asd"w
x" asfdd \f/*sdf asd"d
X" asfdd \f/*sdf asd"dfoobar
/+#LEXERTEST
STRING_HEX,ID,EOL,
STRING_HEX,EOL,
STRING_HEX,EOL,
STRING_HEX,EOL,
ID,STRING_DQ,ID,EOL,
+/
