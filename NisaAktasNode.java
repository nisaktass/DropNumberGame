package dsproje;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nisaaktas
 */
public class NisaAktasNode<T> {
   T value;
   int row,col;
   
   NisaAktasNode<T>next;
   NisaAktasNode<T>prev;
   
   NisaAktasNode<T> up;
   NisaAktasNode<T> down;
   
    
   
   public NisaAktasNode(T value,int row,int col){
       this.value=value;
       this.row=row;
       this.col=col;
   }
    
}
