/*
---GRUPO 3---
Guido Mainardi - 18106136 - guido.mainardi@edu.pucrs.br
Lucas Félix - 18108826 - lucas.salaverry@edu.pucrs.br
Pedro Wagner - 18106192 - pedro.wagner00@edu.pucrs.br
Renata Rittmann = 18110282 - renata.rittmann@edu.pucrs.br
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabSimb {
  private ArrayList<TS_entry> lista;

  public TabSimb() {
    lista = new ArrayList<TS_entry>();
  }

  public void insert(TS_entry nodo) {
    lista.add(nodo);
  }

  public void listar(boolean base) {
    if(base)
    {
      System.out.println("\n\nListagem da tabela de simbolos:\n");
      for (TS_entry nodo : lista) {
        System.out.println(nodo);
        if(nodo.getClasse() == ClasseID.NomeStruct || nodo.getClasse() == ClasseID.NomeFuncao)
        {
          nodo.getTabSimb().listar(false);
        }
      }
    }
    else
    {
      for (TS_entry nodo : lista) {
        System.out.println("  "+nodo);
      }
    }
  }

  public List<TS_entry> getParams()
  {
    return lista.stream().filter(p -> p.getClasse() == ClasseID.NomeParam).toList();
  }

  public TS_entry pesquisa(String umId, ClasseID currClass) {
    for (TS_entry nodo : lista) {

      if (currClass == ClasseID.VarLocal)
      {
        if(nodo.getClasse() == ClasseID.NomeParam && nodo.getId().equals(umId))
          return nodo;
      }

      if (nodo.getId().equals(umId) && (nodo.getClasse() == currClass || currClass == ClasseID.ANY)) {
        return nodo;
      }

      if( nodo.getClasse() == ClasseID.NomeStruct)
      {
        TS_entry aux = nodo.getTabSimb().pesquisa(umId, currClass);
        if (aux != null)
          return aux;
      }
    }
    return null;
  }

  public ArrayList<TS_entry> getLista() {
    return lista;
  }
}
