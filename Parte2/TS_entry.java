/*
---GRUPO 3---
Guido Mainardi - 18106136 - guido.mainardi@edu.pucrs.br
Lucas Félix - 18108826 - lucas.salaverry@edu.pucrs.br
Pedro Wagner - 18106192 - pedro.wagner00@edu.pucrs.br
Renata Rittmann = 18110282 - renata.rittmann@edu.pucrs.br
*/
public class TS_entry
{
   private String id;
   private int tipo;
   private int nElem;
   private int tipoBase;


   public TS_entry(String umId, int umTipo, int ne, int umTBase) {
      id = umId;
      tipo = umTipo;
      nElem = ne;
      tipoBase = umTBase;
   }

   public TS_entry(String umId, int umTipo) {
      this(umId, umTipo, -1, -1);
   }


   public String getId() {
       return id; 
   }

   public int getTipo() {
       return tipo; 
   }
   
   public int getNumElem() {
       return nElem; 
   }

   public int getTipoBase() {
       return tipoBase; 
   }

   
   public String toString() {
       String aux = (nElem != -1) ? "\t array(" + nElem + "): "+tipoBase : "";
       return "Id: " + id + "\t tipo: " + tipo + aux;
   }


}
