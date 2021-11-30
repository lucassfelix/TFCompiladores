#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 9 "parte2.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
#line 10 "y.tab.c"
#define ID 257
#define INT 258
#define FLOAT 259
#define BOOL 260
#define NUM 261
#define LIT 262
#define VOID 263
#define MAIN 264
#define READ 265
#define WRITE 266
#define IF 267
#define ELSE 268
#define WHILE 269
#define TRUE 270
#define FALSE 271
#define EQ 272
#define LEQ 273
#define GEQ 274
#define NEQ 275
#define AND 276
#define OR 277
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    3,    0,    5,    7,    4,    2,    2,    8,    1,    1,
    1,    6,    6,    9,    9,    9,   11,    9,    9,   12,
   13,    9,   14,    9,   16,   15,   15,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,
};
short yylen[] = {                                         2,
    0,    3,    0,    0,    9,    2,    0,    3,    1,    1,
    1,    2,    0,    4,    3,    5,    0,    8,    5,    0,
    0,    7,    0,    7,    0,    3,    0,    1,    1,    1,
    1,    3,    2,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,
};
short yydefred[] = {                                      1,
    0,    0,    9,   10,   11,    0,    0,    0,    0,    0,
    2,    6,    8,    0,    0,    3,    0,   13,    0,    0,
    0,    0,    0,   20,   13,    0,   12,    0,    0,    0,
    0,    0,    0,    5,   31,   28,   29,   30,    0,    0,
    0,    0,    0,    0,    0,   15,   33,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   14,    0,    0,    0,    0,    0,   32,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   36,   37,
   38,   19,   16,    0,    0,   21,    0,    0,    0,    0,
   25,   24,   22,   18,    0,   26,
};
short yydgoto[] = {                                       1,
    6,    7,    2,   11,   17,   19,   26,    8,   27,   41,
   65,   32,   89,   66,   92,   95,
};
short yysindex[] = {                                      0,
    0, -174,    0,    0,    0, -256, -261, -174,  -55, -255,
    0,    0,    0,  -22,  -15,    0,  -95,    0, -120,    3,
    7,   31,   47,    0,    0,  -56,    0,   -9, -181, -184,
   -9,   52, -109,    0,    0,    0,    0,    0,   -9,   -9,
  -37,   53,   58,    6,   -9,    0,    0,  -30,   -9,   -9,
   -9,   -9,   -9,   -9,   -9,   -9,   -9,   -9,   -9,   -9,
   -9,    0,   34,   41,   61,   65,   -8,    0,   97,   97,
   97,   97,   20,   13,   97,   97,   83,   83,    0,    0,
    0,    0,    0,   -9, -120,    0,   -1, -161, -120,   54,
    0,    0,    0,    0, -120,    0,
};
short yyrindex[] = {                                      0,
    0, -151,    0,    0,    0,    0,    0, -151,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   75,   85,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   42,   49,
   56,   62,  -21,  -14,   69,   76,   29,   36,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -104,    0,    0,
    0,    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,  124,    0,    0,    0,  116,    0,    0,   38,  128,
    0,    0,    0,    0,    0,    0,
};
#define YYTABLESIZE 353
short yytable[] = {                                      61,
    9,   10,   25,   13,   59,   57,   61,   58,   14,   60,
   68,   59,   57,   25,   58,   46,   60,   15,   27,   46,
   27,   62,   56,   39,   55,   16,   45,   18,   61,   56,
   40,   55,   86,   59,   57,   61,   58,   46,   60,   90,
   59,   57,   61,   58,   45,   60,   29,   59,   57,   61,
   58,   56,   60,   55,   59,   57,   61,   58,   56,   60,
   55,   59,   57,   28,   58,   56,   60,   55,   34,   34,
   30,   34,   56,   34,   55,   42,   35,   43,   35,   56,
   35,   55,   41,    3,    4,    5,   31,   34,   34,   42,
   34,   45,   82,   63,   35,   35,   43,   35,   64,   83,
   41,   41,   44,   41,   84,   85,   91,   42,   42,   39,
   42,    7,   94,    4,   43,   43,   40,   43,   17,   61,
   44,   44,   88,   44,   59,   23,   93,   39,   39,   60,
   39,   12,   96,   61,   40,   40,   20,   40,   59,   57,
   33,   58,    0,   60,   21,   22,   23,   20,   24,    0,
    0,    0,   27,    0,    0,   21,   22,   23,   44,   24,
   27,   27,   27,    0,   27,    0,   47,   48,    0,    0,
    0,    0,   67,    0,    0,    0,   69,   70,   71,   72,
   73,   74,   75,   76,   77,   78,   79,   80,   81,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   87,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   49,   50,   51,   52,   53,   54,
    0,   49,   50,   51,   52,   53,   54,   35,    0,    0,
    0,   36,    0,    0,   46,   46,    0,    0,    0,    0,
   37,   38,   45,   49,   50,   51,   52,   53,   54,    0,
   49,   50,   51,   52,   53,   54,    0,   49,   50,   51,
   52,   53,   54,    0,   49,   50,   51,   52,   53,    0,
    0,   49,   50,   51,   52,    0,    0,    0,    0,    0,
   34,   34,   34,   34,   34,   34,    0,   35,   35,   35,
   35,   35,   35,   41,   41,   41,   41,   41,   41,    0,
   42,   42,   42,   42,   42,   42,    0,   43,   43,   43,
   43,   43,   43,   44,   44,   44,   44,   44,   44,    0,
   39,   39,   39,   39,   39,   39,    0,   40,   40,   40,
   40,   40,   40,
};
short yycheck[] = {                                      37,
  257,  263,  123,   59,   42,   43,   37,   45,  264,   47,
   41,   42,   43,  123,   45,  125,   47,   40,  123,   41,
  125,   59,   60,   33,   62,   41,   41,  123,   37,   60,
   40,   62,   41,   42,   43,   37,   45,   59,   47,   41,
   42,   43,   37,   45,   59,   47,   40,   42,   43,   37,
   45,   60,   47,   62,   42,   43,   37,   45,   60,   47,
   62,   42,   43,   61,   45,   60,   47,   62,  125,   41,
   40,   43,   60,   45,   62,  257,   41,  262,   43,   60,
   45,   62,   41,  258,  259,  260,   40,   59,   60,   41,
   62,   40,   59,   41,   59,   60,   41,   62,   41,   59,
   59,   60,   41,   62,   44,   41,  268,   59,   60,   41,
   62,  263,   59,  125,   59,   60,   41,   62,   44,   37,
   59,   60,   85,   62,   42,   41,   89,   59,   60,   47,
   62,    8,   95,   37,   59,   60,  257,   62,   42,   43,
   25,   45,   -1,   47,  265,  266,  267,  257,  269,   -1,
   -1,   -1,  257,   -1,   -1,  265,  266,  267,   31,  269,
  265,  266,  267,   -1,  269,   -1,   39,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   49,   50,   51,   52,
   53,   54,   55,   56,   57,   58,   59,   60,   61,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   84,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  272,  273,  274,  275,  276,  277,
   -1,  272,  273,  274,  275,  276,  277,  257,   -1,   -1,
   -1,  261,   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,
  270,  271,  277,  272,  273,  274,  275,  276,  277,   -1,
  272,  273,  274,  275,  276,  277,   -1,  272,  273,  274,
  275,  276,  277,   -1,  272,  273,  274,  275,  276,   -1,
   -1,  272,  273,  274,  275,   -1,   -1,   -1,   -1,   -1,
  272,  273,  274,  275,  276,  277,   -1,  272,  273,  274,
  275,  276,  277,  272,  273,  274,  275,  276,  277,   -1,
  272,  273,  274,  275,  276,  277,   -1,  272,  273,  274,
  275,  276,  277,  272,  273,  274,  275,  276,  277,   -1,
  272,  273,  274,  275,  276,  277,   -1,  272,  273,  274,
  275,  276,  277,
};
#define YYFINAL 1
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 277
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'!'",0,0,0,"'%'",0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,
0,0,0,0,"';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"'{'",0,
"'}'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ID","INT","FLOAT","BOOL","NUM","LIT","VOID","MAIN",
"READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","EQ","LEQ","GEQ","NEQ","AND",
"OR",
};
char *yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type ID ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : ID '=' exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$4 :",
"cmd : WRITE '(' LIT $$4 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"$$5 :",
"$$6 :",
"cmd : WHILE $$5 '(' exp ')' $$6 cmd",
"$$7 :",
"cmd : IF '(' exp $$7 ')' cmd restoIf",
"$$8 :",
"restoIf : ELSE $$8 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
};
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 170 "parte2.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private int proxRot = 1;


  public static int ARRAY = 100;


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
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EBX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t Guido Mainardi - 18106136 - guido.mainardi@edu.pucrs.br\n
								Lucas Felix - 18108826 - lucas.salaverry\@edu.pucrs.br \n
								Pedro Wagner - 18106192 - pedro.wagner00\@edu.pucrs.br \n
								Renata Rittmann = 18110282 - renata.rittmann@edu.pucrs.br \n#\n"
                                ); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
#line 515 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 36 "parte2.y"
{ geraInicio(); }
break;
case 2:
#line 36 "parte2.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
#line 38 "parte2.y"
{ System.out.println("_start:"); }
break;
case 4:
#line 39 "parte2.y"
{ geraFinal(); }
break;
case 8:
#line 44 "parte2.y"
{  TS_entry nodo = ts.pesquisa(yyvsp[-1].sval);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + yyvsp[-1].sval + "< jah declarada");
                        else ts.insert(new TS_entry(yyvsp[-1].sval, yyvsp[-2].ival)); }
break;
case 9:
#line 50 "parte2.y"
{ yyval.ival = INT; }
break;
case 10:
#line 51 "parte2.y"
{ yyval.ival = FLOAT; }
break;
case 11:
#line 52 "parte2.y"
{ yyval.ival = BOOL; }
break;
case 14:
#line 59 "parte2.y"
{  System.out.println("\tPOPL %EDX");
  						   System.out.println("\tMOVL %EDX, _"+yyvsp[-3].sval);
					     }
break;
case 15:
#line 62 "parte2.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 16:
#line 65 "parte2.y"
{ strTab.add(yyvsp[-2].sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 17:
#line 74 "parte2.y"
{ strTab.add(yyvsp[0].sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 18:
#line 82 "parte2.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 19:
#line 89 "parte2.y"
{
									System.out.println("\tPUSHL $_"+yyvsp[-2].sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 20:
#line 97 "parte2.y"
{
					pRot.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRot.peek());
				  }
break;
case 21:
#line 101 "parte2.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
										}
break;
case 22:
#line 106 "parte2.y"
{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
							System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
							pRot.pop();
							}
break;
case 23:
#line 112 "parte2.y"
{	
											pRot.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
break;
case 24:
#line 121 "parte2.y"
{
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
										}
break;
case 25:
#line 128 "parte2.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());
								
										}
break;
case 27:
#line 136 "parte2.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				}
break;
case 28:
#line 143 "parte2.y"
{ System.out.println("\tPUSHL $"+yyvsp[0].sval); }
break;
case 29:
#line 144 "parte2.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 30:
#line 145 "parte2.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 31:
#line 146 "parte2.y"
{ System.out.println("\tPUSHL _"+yyvsp[0].sval); }
break;
case 33:
#line 148 "parte2.y"
{ gcExpNot(); }
break;
case 34:
#line 150 "parte2.y"
{ gcExpArit('+'); }
break;
case 35:
#line 151 "parte2.y"
{ gcExpArit('-'); }
break;
case 36:
#line 152 "parte2.y"
{ gcExpArit('*'); }
break;
case 37:
#line 153 "parte2.y"
{ gcExpArit('/'); }
break;
case 38:
#line 154 "parte2.y"
{ gcExpArit('%'); }
break;
case 39:
#line 156 "parte2.y"
{ gcExpRel('>'); }
break;
case 40:
#line 157 "parte2.y"
{ gcExpRel('<'); }
break;
case 41:
#line 158 "parte2.y"
{ gcExpRel(EQ); }
break;
case 42:
#line 159 "parte2.y"
{ gcExpRel(LEQ); }
break;
case 43:
#line 160 "parte2.y"
{ gcExpRel(GEQ); }
break;
case 44:
#line 161 "parte2.y"
{ gcExpRel(NEQ); }
break;
case 45:
#line 163 "parte2.y"
{ gcExpLog(OR); }
break;
case 46:
#line 164 "parte2.y"
{ gcExpLog(AND); }
break;
#line 864 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
