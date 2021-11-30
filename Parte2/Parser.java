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






//#line 9 "parte2.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




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
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short TRUE=270;
public final static short FALSE=271;
public final static short BREAK=272;
public final static short CONTINUE=273;
public final static short INCR=274;
public final static short FOR=275;
public final static short DECR=276;
public final static short EQ=277;
public final static short LEQ=278;
public final static short GEQ=279;
public final static short NEQ=280;
public final static short AND=281;
public final static short OR=282;
public final static short ADDATRIB=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    4,    0,    6,    8,    5,    3,    3,    9,    2,    2,
    1,    1,    1,    7,    7,   10,   10,   10,   12,   10,
   10,   10,   10,   13,   14,   10,   15,   18,   19,   20,
   21,   10,   22,   10,   16,   16,   17,   17,   24,   23,
   23,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,   11,   11,
   11,   11,   11,   11,   11,   11,   11,   11,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    4,    3,    0,
    1,    1,    1,    2,    0,    2,    3,    5,    0,    8,
    5,    2,    2,    0,    0,    7,    0,    0,    0,    0,
    0,   14,    0,    7,    1,    0,    1,    0,    0,    3,
    0,    1,    1,    1,    1,    2,    2,    2,    2,    3,
    3,    2,    4,    6,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,
};
final static short yydefred[] = {                         1,
    0,    0,   11,   12,   13,    0,    0,    0,    0,    0,
    0,    2,    6,    0,    0,    0,    9,    8,    0,    3,
    0,   15,    0,    0,   42,    0,    0,    0,   24,   43,
   44,    0,    0,    0,   27,    0,    0,    0,   15,    0,
   14,    0,   47,   49,    0,    0,    0,    0,    0,    0,
    0,   22,   23,   46,    0,   48,   52,    0,    0,    5,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   16,    0,   50,    0,    0,    0,    0,
    0,    0,   51,   17,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,   59,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,   18,    0,    0,
   25,   28,    0,    0,    0,    0,    0,    0,   39,   34,
   26,    0,   29,   20,    0,    0,   40,   30,    0,    0,
   31,    0,   32,
};
final static short yydgoto[] = {                          1,
    6,   10,    7,    2,   12,   21,   23,   40,    8,   41,
   42,  101,   51,  116,   55,  105,  123,  117,  126,  129,
  132,  102,  120,  125,
};
final static short yysindex[] = {                         0,
    0, -212,    0,    0,    0,  -89, -260, -212, -252, -243,
 -246,    0,    0,  -73,  -32,   -3,    0,    0,  -10,    0,
  -79,    0,  123,  147,    0,   28,   30,   35,    0,    0,
    0,   -7,   29, -170,    0, -168,  148,  148,    0,  -33,
    0,   -9,    0,    0,  148,  148,  148, -160, -149,  148,
   80,    0,    0,    0,   83,    0,    0,   -2,   82,    0,
  148,  148,  148,  148,  148,  148,  148,  148,  148,  148,
  148,  148,  148,    0,   48,    0,   12,   84,   85,   48,
  148,  148,    0,    0,   64,   64,   64,   64,  124,   57,
   64,   64,  -21,  -21,    0,    0,    0,   63,   69,   70,
   86,   90,   24,   48,   73,  148,    0,    0,  148,  123,
    0,    0,   48,   36, -135,  123,  148,   77,    0,    0,
    0,   48,    0,    0,  123,   78,    0,    0,  148,   97,
    0,  123,    0,
};
final static short yyrindex[] = {                         0,
    0, -124,    0,    0,    0, -117,    0, -124,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   16,  -37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   21,    0,    0,    0,   99,  103,
    0,   87,    0,    0,  169,  226,  323,  366,  -17,   62,
  393,  403,  133,  161,    0,    0,    0,  -30,    0,    0,
    0,    0,    0,  -40,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,  102,    0,   88,    0,    0,    0,
    0,   89,    0,    0,    0,    0,    0,    0,  104,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  141,    0,    0,    0,  111,    0,    0,    2,
  452,    0,    0,    0,    0,   22,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=685;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         45,
   35,    9,   11,   45,   45,   45,   53,   45,   14,   45,
   53,   53,   53,   15,   53,   73,   53,   16,   35,   17,
   71,   45,   45,   68,   45,   72,   18,   73,   53,   53,
   20,   53,   71,   69,   73,   70,   19,   72,   83,   71,
   69,   68,   70,   22,   72,    3,    4,    5,   73,   74,
   68,   52,   67,   71,   69,   45,   70,   68,   72,   67,
   73,   55,   53,   54,  111,   71,   69,   48,   70,   49,
   72,   68,   73,   67,   50,   68,  118,   71,   69,   55,
   70,   54,   72,   68,   73,   67,   54,   53,   56,   71,
   69,   60,   70,   73,   72,   68,   78,   67,   71,   69,
   73,   70,   67,   72,   98,   71,   69,   68,   70,   67,
   72,  115,   79,   55,   37,   54,   68,  121,   67,   81,
   67,   38,   82,  106,   99,  100,  127,  107,  108,  109,
  110,  112,  119,  133,   41,  124,  128,  131,    7,   10,
    4,   41,   19,   33,   36,   36,   38,   37,   13,   59,
  130,    0,    0,    0,   67,   37,    0,    0,    0,    0,
   73,    0,   38,    0,    0,   71,   69,    0,   70,    0,
   72,    0,    0,   56,    0,   56,    0,   56,    0,    0,
   37,    0,    0,   68,    0,   67,    0,   38,    0,    0,
    0,   56,   56,    0,   56,    0,    0,    0,    0,    0,
    0,   57,    0,   57,   39,   57,   84,   45,    0,   63,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   57,    0,   57,    0,   41,   56,   41,   63,   63,    0,
   63,    0,    0,    0,    0,    0,    0,   47,    0,   45,
   45,   45,   45,   45,   45,   39,   53,   53,   53,   53,
   53,   53,    0,   57,    0,    0,    0,    0,    0,    0,
    0,   63,    0,   68,   68,    0,   64,   61,   62,   63,
   64,   65,   66,    0,   61,   62,   63,   64,   65,   66,
    0,    0,    0,    0,   64,   64,    0,   64,   61,   62,
   63,   64,   65,   66,    0,    0,    0,    0,    0,    0,
   61,   62,   63,   64,   65,   66,    0,    0,    0,    0,
    0,    0,   61,   62,   63,   64,   65,   66,   64,    0,
    0,    0,    0,    0,   61,   62,   63,   64,   65,   66,
    0,    0,    0,   61,   62,   63,   64,   65,   24,    0,
    0,    0,   25,   67,    0,    0,   26,   27,   28,    0,
   29,   30,   31,   32,   33,   34,   35,   36,   41,    0,
    0,    0,   41,   65,    0,    0,   41,   41,   41,    0,
   41,   41,   41,   41,   41,   41,   41,   41,    0,   24,
    0,   65,   65,   25,   65,    0,    0,   26,   27,   28,
    0,   29,   30,   31,   32,   33,   34,   35,   36,    0,
   61,   62,   63,   64,   24,    0,   66,    0,   25,   56,
   56,   56,   56,   56,   56,   65,    0,   30,   31,    0,
   43,   34,   44,   36,   66,   66,    0,   66,    0,   46,
    0,    0,    0,   61,    0,    0,    0,   57,   57,   57,
   57,   57,   57,   62,    0,   63,   63,   63,   63,   63,
   63,   61,   61,    0,   61,    0,    0,    0,   66,    0,
    0,   62,   62,    0,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   61,    0,    0,   57,   58,
    0,    0,    0,    0,    0,   62,   75,   76,   77,    0,
    0,   80,   64,   64,   64,   64,   64,   64,    0,    0,
    0,    0,   85,   86,   87,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,    0,    0,    0,    0,    0,
    0,    0,  103,  104,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  113,    0,    0,
  114,    0,    0,    0,    0,    0,    0,    0,  122,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  104,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   65,
   65,   65,   65,   65,   65,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,   66,   66,   66,   66,   66,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   61,
   61,   61,   61,   61,   61,    0,    0,    0,    0,   62,
   62,   62,   62,   62,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,   91,  263,   41,   42,   43,   37,   45,  261,   47,
   41,   42,   43,  257,   45,   37,   47,  264,   59,   93,
   42,   59,   60,   41,   62,   47,   59,   37,   59,   60,
   41,   62,   42,   43,   37,   45,   40,   47,   41,   42,
   43,   59,   45,  123,   47,  258,  259,  260,   37,   59,
   60,   59,   62,   42,   43,   93,   45,   60,   47,   62,
   37,   41,   93,   41,   41,   42,   43,   40,   45,   40,
   47,   60,   37,   62,   40,   93,   41,   42,   43,   59,
   45,   59,   47,   60,   37,   62,  257,   59,  257,   42,
   43,  125,   45,   37,   47,   60,  257,   62,   42,   43,
   37,   45,   41,   47,   93,   42,   43,   60,   45,   62,
   47,  110,  262,   93,   33,   93,   60,  116,   62,   40,
   59,   40,   40,   61,   41,   41,  125,   59,   59,   44,
   41,   59,  268,  132,   33,   59,   59,   41,  263,  257,
  125,   40,   44,   41,   41,   59,   59,   59,    8,   39,
  129,   -1,   -1,   -1,   93,   33,   -1,   -1,   -1,   -1,
   37,   -1,   40,   -1,   -1,   42,   43,   -1,   45,   -1,
   47,   -1,   -1,   41,   -1,   43,   -1,   45,   -1,   -1,
   33,   -1,   -1,   60,   -1,   62,   -1,   40,   -1,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   43,  123,   45,  125,   61,   -1,   41,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,  123,   93,  125,   59,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,  277,
  278,  279,  280,  281,  282,  123,  277,  278,  279,  280,
  281,  282,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,  281,  282,   -1,   41,  277,  278,  279,
  280,  281,  282,   -1,  277,  278,  279,  280,  281,  282,
   -1,   -1,   -1,   -1,   59,   60,   -1,   62,  277,  278,
  279,  280,  281,  282,   -1,   -1,   -1,   -1,   -1,   -1,
  277,  278,  279,  280,  281,  282,   -1,   -1,   -1,   -1,
   -1,   -1,  277,  278,  279,  280,  281,  282,   93,   -1,
   -1,   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  257,   -1,
   -1,   -1,  261,  282,   -1,   -1,  265,  266,  267,   -1,
  269,  270,  271,  272,  273,  274,  275,  276,  257,   -1,
   -1,   -1,  261,   41,   -1,   -1,  265,  266,  267,   -1,
  269,  270,  271,  272,  273,  274,  275,  276,   -1,  257,
   -1,   59,   60,  261,   62,   -1,   -1,  265,  266,  267,
   -1,  269,  270,  271,  272,  273,  274,  275,  276,   -1,
  277,  278,  279,  280,  257,   -1,   41,   -1,  261,  277,
  278,  279,  280,  281,  282,   93,   -1,  270,  271,   -1,
  274,  274,  276,  276,   59,   60,   -1,   62,   -1,  283,
   -1,   -1,   -1,   41,   -1,   -1,   -1,  277,  278,  279,
  280,  281,  282,   41,   -1,  277,  278,  279,  280,  281,
  282,   59,   60,   -1,   62,   -1,   -1,   -1,   93,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   37,   38,
   -1,   -1,   -1,   -1,   -1,   93,   45,   46,   47,   -1,
   -1,   50,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,   -1,   61,   62,   63,   64,   65,   66,   67,   68,
   69,   70,   71,   72,   73,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   81,   82,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  106,   -1,   -1,
  109,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  117,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  129,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
  278,  279,  280,  281,  282,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
  278,  279,  280,  281,  282,   -1,   -1,   -1,   -1,  277,
  278,  279,  280,  281,  282,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM",
"LIT","VOID","MAIN","READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","BREAK",
"CONTINUE","INCR","FOR","DECR","EQ","LEQ","GEQ","NEQ","AND","OR","ADDATRIB",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type tarray ID ';'",
"tarray : '[' NUM ']'",
"tarray :",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$4 :",
"cmd : WRITE '(' LIT $$4 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"cmd : BREAK ';'",
"cmd : CONTINUE ';'",
"$$5 :",
"$$6 :",
"cmd : WHILE $$5 '(' exp ')' $$6 cmd",
"$$7 :",
"$$8 :",
"$$9 :",
"$$10 :",
"$$11 :",
"cmd : FOR $$7 '(' expOpc ';' $$8 expOpc2 $$9 ';' $$10 expOpc ')' $$11 cmd",
"$$12 :",
"cmd : IF '(' exp $$12 ')' cmd restoIf",
"expOpc : exp",
"expOpc :",
"expOpc2 : exp",
"expOpc2 :",
"$$13 :",
"restoIf : ELSE $$13 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : INCR ID",
"exp : ID INCR",
"exp : DECR ID",
"exp : ID DECR",
"exp : ID ADDATRIB exp",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : ID '[' exp ']'",
"exp : ID '[' exp ']' '=' exp",
"exp : ID '=' exp",
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

//#line 266 "parte2.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRotRep = new Stack<Integer>();
  private Stack<Integer> pRotSel = new Stack<Integer>();

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
			System.out.println(".text\n\n#\tGuido Mainardi-18106136\n#\tLucas Felix-18108826\n#\tPedro Wagner-18106192\n#\tRenata Rittmann-18110282\n#\n"); 
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
   
//#line 693 "Parser.java"
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
//#line 37 "parte2.y"
{ geraInicio(); }
break;
case 2:
//#line 37 "parte2.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 39 "parte2.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 40 "parte2.y"
{ geraFinal(); }
break;
case 8:
//#line 47 "parte2.y"
{ TS_entry nodo = ts.pesquisa(val_peek(1).sval);
						if (nodo != null)
							yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
						else ts.insert(new TS_entry(val_peek(1).sval, val_peek(3).ival, val_peek(2).ival, val_peek(3).ival));}
break;
case 9:
//#line 54 "parte2.y"
{yyval.ival = Integer.parseInt(val_peek(1).sval);}
break;
case 10:
//#line 55 "parte2.y"
{yyval.ival = -1;}
break;
case 11:
//#line 60 "parte2.y"
{ yyval.ival = INT; }
break;
case 12:
//#line 61 "parte2.y"
{ yyval.ival = FLOAT; }
break;
case 13:
//#line 62 "parte2.y"
{ yyval.ival = BOOL; }
break;
case 16:
//#line 69 "parte2.y"
{System.out.println("\tPOPL %EDX");}
break;
case 17:
//#line 71 "parte2.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 18:
//#line 73 "parte2.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 19:
//#line 82 "parte2.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 20:
//#line 90 "parte2.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 21:
//#line 97 "parte2.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 22:
//#line 104 "parte2.y"
{System.out.printf("\tJMP rot_%02d",(int)pRotRep.peek() + 1);}
break;
case 23:
//#line 105 "parte2.y"
{System.out.printf("\tJMP rot_%02d",pRotRep.peek());}
break;
case 24:
//#line 106 "parte2.y"
{
					pRotRep.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRotRep.peek());
				  }
break;
case 25:
//#line 110 "parte2.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
										}
break;
case 26:
//#line 115 "parte2.y"
{
				  			System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
							System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
							pRotRep.pop();
							}
break;
case 27:
//#line 121 "parte2.y"
{pRotRep.push(proxRot); proxRot += 4;}
break;
case 28:
//#line 123 "parte2.y"
{
				System.out.printf("rot_%02d:\n",pRotRep.peek()+3);
			}
break;
case 29:
//#line 127 "parte2.y"
{
				System.out.println("\tPOPL %EAX   #Pula se falso...");
				System.out.println("\tCMPL $0, %EAX");
				System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
				System.out.printf("\tJMP rot_%02d   # Continua caso não seja falso\n", pRotRep.peek() + 2);
			}
break;
case 30:
//#line 134 "parte2.y"
{
				  System.out.printf("rot_%02d:\n",pRotRep.peek());
			 }
break;
case 31:
//#line 138 "parte2.y"
{
				System.out.printf("\tJMP rot_%02d   # Volta para a verificação\n", pRotRep.peek()+3);
				System.out.printf("rot_%02d:\n",pRotRep.peek()+2);
			 }
break;
case 32:
//#line 143 "parte2.y"
{
				System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
				System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
				pRotRep.pop();
			}
break;
case 33:
//#line 149 "parte2.y"
{	
									pRotSel.push(proxRot);  proxRot += 2;
													
									System.out.println("\tPOPL %EAX");
									System.out.println("\tCMPL $0, %EAX");
									System.out.printf("\tJE rot_%02d\n", pRotSel.peek());
								}
break;
case 34:
//#line 158 "parte2.y"
{
									System.out.printf("rot_%02d:\n",pRotSel.peek()+1);
									pRotSel.pop();
								}
break;
case 38:
//#line 165 "parte2.y"
{System.out.println("\tPUSHL $1");}
break;
case 39:
//#line 167 "parte2.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
											System.out.printf("rot_%02d:\n",pRotSel.peek());
								
										}
break;
case 41:
//#line 175 "parte2.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
				System.out.printf("rot_%02d:\n",pRotSel.peek());
				}
break;
case 42:
//#line 182 "parte2.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 43:
//#line 183 "parte2.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 44:
//#line 184 "parte2.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 45:
//#line 185 "parte2.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 46:
//#line 186 "parte2.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval);
				System.out.println("\tPOPL %EDX");
				System.out.println("\tADDL $1, %EDX" );
				System.out.println("\tPUSHL %EDX");
  				System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);}
break;
case 47:
//#line 192 "parte2.y"
{ System.out.println("\tPUSHL _"+val_peek(1).sval);
				System.out.println("\tPUSHL _"+val_peek(1).sval);
				System.out.println("\tPOPL %EDX");
				System.out.println("\tADDL $1, %EDX" );
  				System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);}
break;
case 48:
//#line 198 "parte2.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval);
				System.out.println("\tPOPL %EDX");
				System.out.println("\tADDL $-1, %EDX" );
				System.out.println("\tPUSHL %EDX");
  				System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);}
break;
case 49:
//#line 204 "parte2.y"
{ System.out.println("\tPUSHL _"+val_peek(1).sval);
				System.out.println("\tPUSHL _"+val_peek(1).sval);
				System.out.println("\tPOPL %EDX");
				System.out.println("\tADDL $-1, %EDX" );
  				System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);}
break;
case 50:
//#line 211 "parte2.y"
{System.out.println("\tPUSHL _"+val_peek(2).sval);
					   gcExpArit('+');
					   System.out.println("\tPOPL %EDX");
					   System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
					System.out.println("\tPUSHL %EDX");
	 }
break;
case 52:
//#line 219 "parte2.y"
{ gcExpNot(); }
break;
case 53:
//#line 222 "parte2.y"
{
		System.out.println("\tPUSHL $4");
		gcExpArit('*');
		System.out.println("\tPOPL %EDX");
		System.out.println("\tMOVL _" +val_peek(3).sval + "( %EDX ) , %EAX");
		System.out.println("\tPUSHL %EAX");
	}
break;
case 54:
//#line 231 "parte2.y"
{
		System.out.println("\tPOPL %EDX");
		System.out.println("\tPUSHL $4");
		gcExpArit('*');
		System.out.println("\tPOPL %EAX");
		System.out.println("\tMOVL %EDX, _"+val_peek(5).sval+"(%EAX)");
		System.out.println("\tPUSHL %EDX");
	}
break;
case 55:
//#line 240 "parte2.y"
{   System.out.println("\tPOPL %EDX");
  						System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
						System.out.println("\tPUSHL %EDX");
					     }
break;
case 56:
//#line 247 "parte2.y"
{ gcExpArit('+'); }
break;
case 57:
//#line 248 "parte2.y"
{ gcExpArit('-'); }
break;
case 58:
//#line 249 "parte2.y"
{ gcExpArit('*'); }
break;
case 59:
//#line 250 "parte2.y"
{ gcExpArit('/'); }
break;
case 60:
//#line 251 "parte2.y"
{ gcExpArit('%'); }
break;
case 61:
//#line 253 "parte2.y"
{ gcExpRel('>'); }
break;
case 62:
//#line 254 "parte2.y"
{ gcExpRel('<'); }
break;
case 63:
//#line 255 "parte2.y"
{ gcExpRel(EQ); }
break;
case 64:
//#line 256 "parte2.y"
{ gcExpRel(LEQ); }
break;
case 65:
//#line 257 "parte2.y"
{ gcExpRel(GEQ); }
break;
case 66:
//#line 258 "parte2.y"
{ gcExpRel(NEQ); }
break;
case 67:
//#line 259 "parte2.y"
{ gcExpLog(OR); }
break;
case 68:
//#line 260 "parte2.y"
{ gcExpLog(AND); }
break;
//#line 1178 "Parser.java"
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
