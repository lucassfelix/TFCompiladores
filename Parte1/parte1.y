/*
---GRUPO 3---
Guido Mainardi  - 18106136 - guido.mainardi@edu.pucrs.br
Lucas Félix     - 18108826 - lucas.salaverry@edu.pucrs.br
Pedro Wagner    - 18106192 - pedro.wagner00@edu.pucrs.br
Renata Rittmann - 18110282 - renata.rittmann@edu.pucrs.br
*/  
%{
  import java.io.*;
  import java.util.*;
%}


%token IDENT
%token INT
%token DOUBLE 
%token BOOL
%token NUM
%token LITERAL 
%token AND
%token MAIN
%token IF
%token VOID
%token STRING
%token STRUCT
%token FUNCT
%token TRUE
%token FALSE


%right '='
%nonassoc '>'
%nonassoc ','
%left '+'
%left AND
%left '[' 
%left '(' 
%left '.'

%type <sval> IDENT
%type <ival> NUM
%type <obj> tipo
%type <obj> tipo_funct
%type <obj> exp

%start programa

%%

programa 
  : {currClass = ClasseID.VarGlobal; currEscopo = ts;} lista_declaracoes lista_funcoes;

lista_declaracoes 
  : declaracao lista_declaracoes
  | //Vazio
  ;

declaracao 
  : tipo  {currentType = (TS_entry)$1;} TArray lista_ident ';'
  | declarador_struct
  ;

declarador_struct
  : STRUCT
  IDENT
    {
      TS_entry nodo = currEscopo.pesquisa($2, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + $2 + "< jah declarada");
      else
      {
        TS_entry aux = new TS_entry($2, Tp_STRUCT, ClasseID.NomeStruct);
        currEscopo.insert(aux);
        currEscopo = aux.getTabSimb(); 
      }
    }
  '{' 
    {currClass = ClasseID.CampoStruct;} 
  lista_campos '}' ';'
    {currEscopo = ts;}
    {currClass = ClasseID.VarGlobal;}
  ;

lista_campos
  : lista_campos campoStruct
  | campoStruct
  ;

campoStruct
  : tipo {currentType = (TS_entry)$1;} TArray lista_ident ';'
  ;

tipo
  : INT    { $$ = Tp_INT;}
  | DOUBLE { $$ = Tp_DOUBLE;}
  | BOOL   { $$ = Tp_BOOL;}   
  | STRING { $$ = Tp_STRING;}
  | IDENT
    { TS_entry nodo = ts.pesquisa($1, ClasseID.NomeStruct);
      if (nodo == null) 
      {
        yyerror("(sem) variavel >" + $1 + "< não foi declarada neste escopo");
        $$ = Tp_ERRO;
      }
      else 
      {
        $$ = nodo; 
      }
    }
  ;

tipo_funct 
  : tipo { $$ = (TS_entry)$1;}
  | VOID { $$ = Tp_VOID;}
  ;




TArray 
  : '[' NUM ']' TArray 
    { currentType = new TS_entry("?",Tp_ARRAY, currClass, $2, currentType); }   
  | //Vazio
  ;

lista_ident 
  : lista_ident ',' identificador  
  | identificador
  ;

identificador 
  : IDENT   
    { TS_entry nodo = currEscopo.pesquisa($1, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + $1 + "< jah declarada neste escopo");
      else 
        currEscopo.insert(new TS_entry($1, currentType, currClass,0,currentType.getTipo())); 
    }
  ;

lista_funcoes
  : lista_funcoes funcao
  | //Vazio
  ;

funcao
  : FUNCT tipo_funct {currentType = (TS_entry)$2;}
  IDENT
  {
      TS_entry nodo = currEscopo.pesquisa($4, currClass);
      if (nodo != null) 
        yyerror("(sem) variavel >" + $4 + "< jah declarada neste escopo");
      else
      {
        TS_entry aux = new TS_entry($4, Tp_FUNCT, ClasseID.NomeFuncao,0 , currentType);
        currEscopo.insert(aux);
        currEscopo = aux.getTabSimb(); 
      }
    }
  '('
    {currClass = ClasseID.NomeParam;} 
  lista_parametros ')' 
    {currClass = ClasseID.VarLocal;} 
  lista_declaracoes_funct bloco
   {currClass = ClasseID.VarGlobal;} 
   {currEscopo = ts;}

lista_declaracoes_funct
  : declaracao_funct lista_declaracoes_funct
  | //Vazio

declaracao_funct
  : tipo  {currentType = (TS_entry)$1;} TArray lista_ident ';'
  ;

lista_parametros
  : lista_parametros ',' parametro
  | parametro
  ;

parametro
: tipo {currentType = (TS_entry)$1;} identificador
| //Vazio
;

bloco 
  : '{' listacmd '}';

listacmd 
  : listacmd cmd 
  |
  ;

cmd 
  :  exp ';' 
  | IF '(' exp ')' cmd
    {if ( ((TS_entry)$3) != Tp_BOOL) 
      yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)$3).getTipoStr());
    }     
  ;

exp 
  : exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
  | exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3); }
  | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); } 
  | NUM         { $$ = Tp_INT; }      
  | LITERAL     { $$ = Tp_STRING; }
  | TRUE        { $$ = Tp_BOOL;}
  | FALSE       { $$ = Tp_BOOL;}
  | '(' exp ')' { $$ = $2; }
  | IDENT       
    { TS_entry nodo = currEscopo.pesquisa($1, ClasseID.ANY);
      if (nodo == null) {
          nodo = ts.pesquisa($1, ClasseID.ANY);
          if(nodo == null)
          {
            yyerror("(sem) var <" + $1 + "> nao declarada neste escopo"); 
            $$ = Tp_ERRO;  
          }
          else
          {
            if( nodo.getClasse() == ClasseID.CampoStruct || nodo.getClasse() == ClasseID.NomeFuncao)
              $$ = nodo;
            else
              $$ = nodo.getTipo();
          }
          }           
      else
      {
        if( nodo.getClasse() == ClasseID.CampoStruct || nodo.getClasse() == ClasseID.NomeFuncao)
          $$ = nodo;
        else
          $$ = nodo.getTipo();
      }
    }                   
  | exp '=' exp  {  $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
  | exp '[' exp ']'  
    { if ((TS_entry)$3 != Tp_INT) 
        yyerror("(sem) indexador não é numérico ");
      else 
          if (((TS_entry)$1).getTipo() != Tp_ARRAY)
            yyerror("(sem) elemento não indexado ");
          else 
            $$ = ((TS_entry)$1).getTipoBase();
    } 
  | exp '.' exp 
    { if ( ((TS_entry)$3).getClasse() != ClasseID.CampoStruct)
      {
        yyerror("(sem) <" + ((TS_entry)$3).getId()+"> nao eh campo de struct.");
        $$ = Tp_ERRO;
      }
      else if ( ((TS_entry)$1).getTipoBase() != Tp_STRUCT && ((TS_entry)$1).getTipo() != Tp_STRUCT  )
      {
        yyerror("(sem) <" + ((TS_entry)$1).getId() +"> eh <" + ((TS_entry)$1).getTipoStr() + "> e nao <struct>");
        $$ = Tp_ERRO;
      }
      else
      {
        TS_entry aux = ((TS_entry)$1).getTabSimb().pesquisa(((TS_entry)$3).getId(),ClasseID.CampoStruct);
        if(aux == null)
        {
          yyerror("(sem) <" + ((TS_entry)$3).getId() + "> nao eh campo da struct <" + ((TS_entry)$1).getId()  +">");
          $$ = Tp_ERRO;
        }
        else
          $$ = ((TS_entry)$3).getTipo();
      }  
    }
  | exp 
  '('
    {
      contadorArgumento = new ArrayList<TS_entry>();
    }
  lista_exp
  ')'
    {
      if( ((TS_entry)$1).getClasse() != ClasseID.NomeFuncao)
      {
        yyerror("(sem) <" + ((TS_entry)$1).getId() +"> nao eh um nome valido de funcao cont : " + contadorArgumento.size());
        $$ = Tp_ERRO;
      } 
      else
      {
        List<TS_entry> params = ((TS_entry)$1).getTabSimb().getParams();
        if(contadorArgumento.size() < params.size())
        {
          yyerror("(sem) <" + ((TS_entry)$1).getId() + "> requer " + params.size()+ " argumento(s), porem recebeu " + contadorArgumento.size());
          $$ = Tp_ERRO;
        }
        else{
          for (int i = 0; i < params.size();i++)
            {
              if (params.get(i).getTipo() != contadorArgumento.get(i))
              {
                yyerror("(sem) parametro "+ (i+1) +" esperado tipo = " + params.get(i).getTipo().getTipoStr() + " tipo real = " + contadorArgumento.get(i).getTipoStr() );
                $$ = Tp_ERRO;
              }
            }
            if($$ != Tp_ERRO)
            {
              $$ = ((TS_entry)$1).getTipoBase();
            }
        }
      }

    }
  ;

lista_exp
  : lista_exp ',' exp {contadorArgumento.add(((TS_entry)$3));}
  | exp {contadorArgumento.add(((TS_entry)$1));}
  | //Vazio 
  ;






%%

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
  private List<TS_entry> contadorArgumento;

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

