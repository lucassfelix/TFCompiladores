//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 9 "parte1.y"
  import java.io.*;
  import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short DOUBLE=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short FLOAT=262;
public final static short LITERAL=263;
public final static short AND=264;
public final static short MAIN=265;
public final static short IF=266;
public final static short VOID=267;
public final static short STRING=268;
public final static short STRUCT=269;
public final static short FUNCT=270;
public final static short TRUE=271;
public final static short FALSE=272;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    5,    0,    4,    4,    9,    7,    7,   12,   14,   15,
   11,   13,   13,   17,   16,    1,    1,    1,    1,    1,
    2,    2,    8,    8,   10,   10,   18,    6,    6,   20,
   21,   23,   25,   27,   19,   24,   24,   29,   28,   22,
   22,   31,   30,   30,   26,   32,   32,   33,   33,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,   34,    3,   36,    3,   35,   35,   35,
};
final static short yylen[] = {                            2,
    0,    3,    2,    0,    0,    5,    1,    0,    0,    0,
    9,    2,    1,    0,    5,    1,    1,    1,    1,    1,
    1,    1,    4,    0,    3,    1,    1,    2,    0,    0,
    0,    0,    0,    0,   13,    2,    0,    0,    5,    3,
    1,    0,    3,    0,    3,    2,    0,    2,    5,    3,
    3,    3,    1,    1,    1,    1,    1,    3,    1,    3,
    4,    0,    4,    0,    5,    3,    1,    0,
};
final static short yydefred[] = {                         1,
    0,    0,   20,   16,   17,   18,   19,    0,    5,   29,
    0,    7,    8,    0,    0,    3,    0,    0,    0,    0,
   28,    9,    0,   27,    0,   26,   22,   21,   30,    0,
    0,    0,    6,    0,   14,    0,   13,   23,   25,   31,
    0,    0,   12,    0,    0,   10,   32,    0,   11,    0,
   15,   42,    0,   41,    0,    0,   33,   43,   40,    0,
   38,   34,    0,    0,    0,   36,    0,   47,   35,    0,
    0,   39,   59,   53,   54,   55,    0,   56,   57,    0,
   45,    0,   46,    0,    0,    0,    0,    0,    0,    0,
   64,   62,   48,    0,   58,    0,    0,    0,    0,    0,
    0,    0,    0,   61,    0,    0,   63,   49,    0,   65,
    0,
};
final static short yydgoto[] = {                          1,
    9,   29,   82,   10,    2,   15,   11,   19,   14,   25,
   12,   17,   36,   30,   49,   37,   41,   26,   21,   34,
   44,   53,   50,   62,   60,   69,   65,   63,   64,   54,
   55,   71,   83,  102,  106,  101,
};
final static short yysindex[] = {                         0,
    0, -108,    0,    0,    0,    0,    0, -239,    0,    0,
 -108,    0,    0,  -62, -231,    0,  -78, -204, -192, -104,
    0,    0,  -25,    0,   38,    0,    0,    0,    0,  -92,
  -62, -192,    0, -180,    0, -123,    0,    0,    0,    0,
  -62,   42,    0,   63, -192,    0,    0,   45,    0,  -92,
    0,    0,  -36,    0, -192,  -92,    0,    0,    0,  -92,
    0,    0,  -92,  -62,  -14,    0, -192,    0,    0,   52,
  -40,    0,    0,    0,    0,    0,   74,    0,    0,  -21,
    0,  -37,    0,  -21,  -30,  -21,  -21,  -21,  -21,  -21,
    0,    0,    0,  -20,    0,   16,   -3,    4,  -12,  -10,
  -21,  -21,  -33,    0,   -3,  -29,    0,    0,  -21,    0,
   -3,
};
final static short yyrindex[] = {                         0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0, -192,  115,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -192,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -192,    0,    0,    0,    0,    0,    0,    0,    0,  -27,
    0,    0,    0,    0,    0,  -27,    0,    0,    0,   -7,
    0,    0,   -7, -192,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    5,   19,   -6,   31,    0,
   32,    0,    0,    0,   58,    0,    0,    0,    0,    0,
   64,
};
final static short yygindex[] = {                         0,
   50,    0,   39,  107,    0,    0,    0,   53,    0,   24,
    0,    0,    0,    0,    0,   84,    0,  -28,    0,    0,
    0,    0,    0,   59,    0,    0,    0,    0,    0,   65,
    0,    0,   27,    0,    0,    0,
};
final static int YYTABLESIZE=271;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         80,
    4,   42,   91,   39,   57,   89,   80,   56,   92,   91,
   95,  110,   89,   44,  109,   92,   44,   13,   80,   91,
  103,   93,   89,   87,   88,   92,   58,   91,   18,   91,
   87,   88,   89,   92,   51,   92,   91,   51,   20,   89,
   87,   88,   92,   91,   22,   52,   89,   52,   52,   92,
   87,   88,   51,   90,   51,   91,   23,   87,   88,   60,
   90,   92,   60,   52,   24,   52,   52,   31,   48,   28,
   90,   50,   68,   50,   50,   68,   40,   60,   90,   35,
   90,   32,  104,   38,   81,   35,   51,   90,   32,   50,
   70,   50,   50,   45,   90,   32,   33,   52,   67,   52,
   46,   67,   47,   51,   66,   52,   90,   66,   68,   61,
   72,   60,   61,   84,    2,   37,   67,   16,   85,   43,
   59,   66,   94,   50,   96,   97,   98,   99,  100,  108,
    0,    0,    0,    3,    4,    5,    6,    0,    0,  105,
  107,    0,    0,    0,    7,    0,    0,  111,    3,    4,
    5,    6,    3,    4,    5,    6,    0,    0,    0,    7,
    8,    0,   27,    7,    3,    4,    5,    6,    0,    0,
    0,    0,    0,    0,    0,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   73,    0,    0,    0,
   74,   75,   76,   73,    0,   77,   86,   74,   75,   76,
   78,   79,   77,   86,    0,   73,    0,   78,   79,   74,
   75,   76,    0,   86,    0,    0,    0,    0,    0,   78,
   79,   86,    0,   86,    0,    0,    0,    0,    0,    0,
   86,    0,    0,    0,    0,    0,    0,   86,   52,    0,
    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,  125,   40,   32,   41,   43,   40,   44,   46,   40,
   41,   41,   43,   41,   44,   46,   44,  257,   40,   40,
   41,   59,   43,   61,   62,   46,   55,   40,   91,   40,
   61,   62,   43,   46,   41,   46,   40,   44,  270,   43,
   61,   62,   46,   40,  123,   41,   43,   43,   44,   46,
   61,   62,   59,   91,   61,   40,  261,   61,   62,   41,
   91,   46,   44,   59,  257,   61,   62,   93,   45,   20,
   91,   41,   41,   43,   44,   44,  257,   59,   91,   30,
   91,   44,   93,   31,  125,   36,   93,   91,   44,   59,
   67,   61,   62,   41,   91,   44,   59,   93,   41,   50,
   59,   44,   40,   59,   41,   56,   91,   44,  123,   60,
   59,   93,   63,   40,    0,  123,   64,   11,   80,   36,
   56,   63,   84,   93,   86,   87,   88,   89,   90,  103,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  101,
  102,   -1,   -1,   -1,  268,   -1,   -1,  109,  257,  258,
  259,  260,  257,  258,  259,  260,   -1,   -1,   -1,  268,
  269,   -1,  267,  268,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  268,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,
  261,  262,  263,  257,   -1,  266,  264,  261,  262,  263,
  271,  272,  266,  264,   -1,  257,   -1,  271,  272,  261,
  262,  263,   -1,  264,   -1,   -1,   -1,   -1,   -1,  271,
  272,  264,   -1,  264,   -1,   -1,   -1,   -1,   -1,   -1,
  264,   -1,   -1,   -1,   -1,   -1,   -1,  264,  264,   -1,
  270,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=272;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'","','",
null,"'.'",null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","DOUBLE","BOOL","NUM",
"FLOAT","LITERAL","AND","MAIN","IF","VOID","STRING","STRUCT","FUNCT","TRUE",
"FALSE",
};
final static String yyrule[] = {
"$accept : programa",
"$$1 :",
"programa : $$1 lista_declaracoes lista_funcoes",
"lista_declaracoes : declaracao lista_declaracoes",
"lista_declaracoes :",
"$$2 :",
"declaracao : tipo $$2 TArray lista_ident ';'",
"declaracao : declarador_struct",
"$$3 :",
"$$4 :",
"$$5 :",
"declarador_struct : STRUCT IDENT $$3 '{' $$4 lista_campos '}' ';' $$5",
"lista_campos : lista_campos campoStruct",
"lista_campos : campoStruct",
"$$6 :",
"campoStruct : tipo $$6 TArray lista_ident ';'",
"tipo : INT",
"tipo : DOUBLE",
"tipo : BOOL",
"tipo : STRING",
"tipo : IDENT",
"tipo_funct : tipo",
"tipo_funct : VOID",
"TArray : '[' NUM ']' TArray",
"TArray :",
"lista_ident : lista_ident ',' identificador",
"lista_ident : identificador",
"identificador : IDENT",
"lista_funcoes : lista_funcoes funcao",
"lista_funcoes :",
"$$7 :",
"$$8 :",
"$$9 :",
"$$10 :",
"$$11 :",
"funcao : FUNCT tipo_funct $$7 IDENT $$8 '(' $$9 lista_parametros ')' $$10 lista_declaracoes_funct $$11 bloco",
"lista_declaracoes_funct : declaracao_funct lista_declaracoes_funct",
"lista_declaracoes_funct :",
"$$12 :",
"declaracao_funct : tipo $$12 TArray lista_ident ';'",
"lista_parametros : lista_parametros ',' parametro",
"lista_parametros : parametro",
"$$13 :",
"parametro : tipo $$13 identificador",
"parametro :",
"bloco : '{' listacmd '}'",
"listacmd : listacmd cmd",
"listacmd :",
"cmd : exp ';'",
"cmd : IF '(' exp ')' cmd",
"exp : exp '+' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : FLOAT",
"exp : LITERAL",
"exp : TRUE",
"exp : FALSE",
"exp : '(' exp ')'",
"exp : IDENT",
"exp : exp '=' exp",
"exp : exp '[' exp ']'",
"$$14 :",
"exp : exp '.' $$14 exp",
"$$15 :",
"exp : exp '(' $$15 lista_exp ')'",
"lista_exp : lista_exp ',' exp",
"lista_exp : exp",
"lista_exp :",
};

//#line 332 "parte1.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  public static TS_entry Tp_STRING = new TS_entry("string", null, ClasseID.TipoBase);
  public static TS_entry Tp_VOID = new TS_entry("void", null, ClasseID.TipoBase);
  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);
  public static TS_entry Tp_STRUCT = new TS_entry("struct ", null, ClasseID.TipoBase);
  public static TS_entry Tp_FUNCT = new TS_entry("funct", null, ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private TabSimb currEscopo;
  private ClasseID currClass;
  private TS_entry currentType;
  private Stack<List<TS_entry>> contadorArgumento;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_STRING);
    ts.insert(Tp_ARRAY);
    ts.insert(Tp_STRUCT);
    ts.insert(Tp_FUNCT);

    contadorArgumento = new Stack<List<TS_entry>>();

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar(true);}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

//#line 476 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 52 "parte1.y"
{currClass = ClasseID.VarGlobal; currEscopo = ts;}
break;
case 5:
//#line 60 "parte1.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 8:
//#line 67 "parte1.y"
{
      TS_entry nodo = currEscopo.pesquisa(val_peek(0).sval, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");
      else
      {
        TS_entry aux = new TS_entry(val_peek(0).sval, Tp_STRUCT, ClasseID.NomeStruct);
        currEscopo.insert(aux);
        currEscopo = aux.getTabSimb(); 
      }
    }
break;
case 9:
//#line 79 "parte1.y"
{currClass = ClasseID.CampoStruct;}
break;
case 10:
//#line 81 "parte1.y"
{currEscopo = ts;}
break;
case 11:
//#line 82 "parte1.y"
{currClass = ClasseID.VarGlobal;}
break;
case 14:
//#line 91 "parte1.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 16:
//#line 95 "parte1.y"
{ yyval.obj = Tp_INT;}
break;
case 17:
//#line 96 "parte1.y"
{ yyval.obj = Tp_DOUBLE;}
break;
case 18:
//#line 97 "parte1.y"
{ yyval.obj = Tp_BOOL;}
break;
case 19:
//#line 98 "parte1.y"
{ yyval.obj = Tp_STRING;}
break;
case 20:
//#line 100 "parte1.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval, ClasseID.NomeStruct);
      if (nodo == null) 
      {
        yyerror("(sem) variavel >" + val_peek(0).sval + "< não foi declarada neste escopo");
        yyval.obj = Tp_ERRO;
      }
      else 
      {
        yyval.obj = nodo; 
      }
    }
break;
case 21:
//#line 114 "parte1.y"
{ yyval.obj = (TS_entry)val_peek(0).obj;}
break;
case 22:
//#line 115 "parte1.y"
{ yyval.obj = Tp_VOID;}
break;
case 23:
//#line 123 "parte1.y"
{ currentType = new TS_entry("?",Tp_ARRAY, currClass, val_peek(2).ival, currentType); }
break;
case 27:
//#line 134 "parte1.y"
{ TS_entry nodo = currEscopo.pesquisa(val_peek(0).sval, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada neste escopo");
      else 
      {
        if(currentType.getTipo() == Tp_ARRAY)
          currEscopo.insert(new TS_entry(val_peek(0).sval, currentType, currClass,0,currentType.getTipoBase())); 
        else
          currEscopo.insert(new TS_entry(val_peek(0).sval, currentType, currClass,0,currentType.getTipo())); 
      }
    }
break;
case 30:
//#line 153 "parte1.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 31:
//#line 155 "parte1.y"
{
      TS_entry nodo = currEscopo.pesquisa(val_peek(0).sval, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada neste escopo");
      else
      {
        TS_entry aux = new TS_entry(val_peek(0).sval, Tp_FUNCT, ClasseID.NomeFuncao,0 , currentType);
        currEscopo.insert(aux);
        currEscopo = aux.getTabSimb(); 
      }
    }
break;
case 32:
//#line 167 "parte1.y"
{currClass = ClasseID.NomeParam;}
break;
case 33:
//#line 169 "parte1.y"
{currClass = ClasseID.VarLocal;}
break;
case 34:
//#line 171 "parte1.y"
{currClass = ClasseID.ANY;}
break;
case 35:
//#line 173 "parte1.y"
{currEscopo = ts;}
break;
case 38:
//#line 180 "parte1.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 42:
//#line 189 "parte1.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 49:
//#line 204 "parte1.y"
{if ( ((TS_entry)val_peek(2).obj) != Tp_BOOL) 
      yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(2).obj).getTipoStr());
    }
break;
case 50:
//#line 210 "parte1.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 51:
//#line 211 "parte1.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 52:
//#line 212 "parte1.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 53:
//#line 213 "parte1.y"
{ yyval.obj = Tp_INT; }
break;
case 54:
//#line 214 "parte1.y"
{ yyval.obj = Tp_DOUBLE;}
break;
case 55:
//#line 215 "parte1.y"
{ yyval.obj = Tp_STRING; }
break;
case 56:
//#line 216 "parte1.y"
{ yyval.obj = Tp_BOOL;}
break;
case 57:
//#line 217 "parte1.y"
{ yyval.obj = Tp_BOOL;}
break;
case 58:
//#line 218 "parte1.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 59:
//#line 220 "parte1.y"
{ TS_entry nodo = currEscopo.pesquisa(val_peek(0).sval, currClass);
      if (nodo == null) {
          nodo = ts.pesquisa(val_peek(0).sval, currClass);
          if(nodo == null)
          {
            yyerror("(sem) var <" + val_peek(0).sval + "> da classe <" +currClass.name()+"> nao declarada neste escopo"); 
            yyval.obj = Tp_ERRO;  
          }
          else
          {
            if( nodo.getClasse() == ClasseID.CampoStruct || nodo.getClasse() == ClasseID.NomeFuncao)
              yyval.obj = nodo;
            else
              yyval.obj = nodo.getTipo();
          }
          }           
      else
      {
        if( nodo.getClasse() == ClasseID.CampoStruct || nodo.getClasse() == ClasseID.NomeFuncao)
          yyval.obj = nodo;
        else
          yyval.obj = nodo.getTipo();
      }
    }
break;
case 60:
//#line 244 "parte1.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 61:
//#line 246 "parte1.y"
{ if ((TS_entry)val_peek(1).obj != Tp_INT) 
        yyerror("(sem) indexador não é numérico ");
      else 
          if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
            yyerror("(sem) elemento não indexado ");
          else 
            yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
    }
break;
case 62:
//#line 254 "parte1.y"
{currClass = ClasseID.CampoStruct;}
break;
case 63:
//#line 254 "parte1.y"
{currClass = ClasseID.ANY;    
      if ( ((TS_entry)val_peek(0).obj).getClasse() != ClasseID.CampoStruct)
      {
        yyerror("(sem) <" + ((TS_entry)val_peek(0).obj).getId()+"> nao eh variavel campo de struct.");
        yyval.obj = Tp_ERRO;
      }
      else if ( ((TS_entry)val_peek(3).obj).getTipoBase() != Tp_STRUCT && ((TS_entry)val_peek(3).obj).getTipo() != Tp_STRUCT  )
      {
        yyerror("(sem) <" + ((TS_entry)val_peek(3).obj).getId() +"> eh <" + ((TS_entry)val_peek(3).obj).getTipoStr() + "> e nao <struct>");
        yyval.obj = Tp_ERRO;
      }
      else
      {
        TS_entry aux = ((TS_entry)val_peek(3).obj).getTabSimb().pesquisa(((TS_entry)val_peek(0).obj).getId(),ClasseID.CampoStruct);
        if(aux == null)
        {
          yyerror("(sem) <" + ((TS_entry)val_peek(0).obj).getId() + "> nao eh campo da struct <" + ((TS_entry)val_peek(3).obj).getId()  +">");
          yyval.obj = Tp_ERRO;
        }
        else
          yyval.obj = ((TS_entry)val_peek(0).obj).getTipo();
        
      }  
      
    }
break;
case 64:
//#line 282 "parte1.y"
{
      contadorArgumento.push(new ArrayList<TS_entry>());
    }
break;
case 65:
//#line 287 "parte1.y"
{
      if( ((TS_entry)val_peek(4).obj).getClasse() != ClasseID.NomeFuncao)
      {
        yyerror("(sem) <" + ((TS_entry)val_peek(4).obj).getId() +"> nao eh um nome valido de funcao cont : " + contadorArgumento.peek().size());
        yyval.obj = Tp_ERRO;
      } 
      else
      {
        List<TS_entry> params = ((TS_entry)val_peek(4).obj).getTabSimb().getParams();
        if(contadorArgumento.peek().size() < params.size())
        {
          yyerror("(sem) <" + ((TS_entry)val_peek(4).obj).getId() + "> requer " + params.size()+ " argumento(s), porem recebeu " + contadorArgumento.peek().size());
          yyval.obj = Tp_ERRO;
        }
        else{
          for (int i = 0; i < params.size();i++)
            {
              if (params.get(i).getTipo() != contadorArgumento.peek().get(i))
              {
                yyerror("(sem) parametro "+ (i+1) +" esperado tipo = " + params.get(i).getTipo().getTipoStr() + " tipo real = " + contadorArgumento.peek().get(i).getTipoStr() );
                yyval.obj = Tp_ERRO;
              }
            }
            if(yyval.obj != Tp_ERRO)
            {
              yyval.obj = ((TS_entry)val_peek(4).obj).getTipoBase();
            }
        }
      }
      contadorArgumento.pop();
    }
break;
case 66:
//#line 321 "parte1.y"
{contadorArgumento.peek().add(((TS_entry)val_peek(0).obj));}
break;
case 67:
//#line 322 "parte1.y"
{contadorArgumento.peek().add(((TS_entry)val_peek(0).obj));}
break;
//#line 925 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
