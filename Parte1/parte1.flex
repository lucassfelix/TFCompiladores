/*
---GRUPO 3---
Guido Mainardi - 18106136 - guido.mainardi@edu.pucrs.br
Lucas Félix - 18108826 - lucas.salaverry@edu.pucrs.br
Pedro Wagner - 18106192 - pedro.wagner00@edu.pucrs.br
Renata Rittmann = 18110282 - renata.rittmann@edu.pucrs.br
*/
%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }

%}

NUM = [0-9]+
NL  = \n|\r|\r\n

%%


"$TRACE_ON"  { yyparser.setDebug(true);  }
"$TRACE_OFF" { yyparser.setDebug(false); }
"$MOSTRA_TS" { yyparser.listarTS(); }


/* operators */
"+" | 
"=" |
">" |
";" |
"(" |
")" |
"," |
"\{" |
"\}" |
"\[" | 
"\]"    { return (int) yycharat(0); }

"&&" { return Parser.AND; }

{NUM}  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
         return Parser.NUM; }

int    { return Parser.INT;     }
double  { return Parser.DOUBLE;   }
bool   { return Parser.BOOL; }
string { return Parser.STRING; }
void { return Parser.VOID; }
main { return Parser.MAIN; }
if { return Parser.IF; }
struct { return Parser.STRUCT; }


[a-zA-Z][a-zA-Z_0-9]* { yyparser.yylval = new ParserVal(yytext());
                     return Parser.IDENT; }

\"[^\"]*\" { yyparser.yylval = new ParserVal(yytext());
             return Parser.LITERAL; }



{NL}   {yyline++;}
[ \t]+ { }

.    { System.err.println("Erro: caracter inesperado '"+yytext()+"' na linha "+yyline); return YYEOF; }






