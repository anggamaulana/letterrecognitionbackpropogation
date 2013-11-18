
/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backpropagation;

import java.util.ArrayList;
import java.util.List;
import model.Bobot;
import model.HiddenNode;
import model.InputNode;
import model.OutputNode;
import model.TargetNode;

/**
 *
 
 */
public class Pembelajaran {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        //int Input[][]=
//        int a=0;
//            int banyakInput=4;
//        int [][] inputArray=new int[banyakInput][];int [][] targetArray=new int[banyakInput][];
//      inputArray[a]=new int[]{1,1,0,1,0,1,1,1,1,1,0,1,1,1,0}; targetArray[a++]=new int[]{0,0};//B
//      inputArray[a]=new int[]{1,1,0,1,0,1,1,0,1,1,0,1,1,1,0}; targetArray[a++]=new int[]{0,1};//D
//      inputArray[a]=new int[]{1,1,1,1,0,0,1,1,0,1,0,0,1,0,0}; targetArray[a++]=new int[]{1,0};//F
//      inputArray[a]=new int[]{1,0,1,1,0,1,1,1,1,1,0,1,1,0,1}; targetArray[a++]=new int[]{1,1};//H
//
//        List<InputNode> input=new ArrayList<InputNode>();
//        input.add(new InputNode(1));
//        input.add(new InputNode(1));
//        input.add(new InputNode(0));
//        
//        input.add(new InputNode(1));
//        input.add(new InputNode(0));
//        input.add(new InputNode(1));
//        
//        input.add(new InputNode(1));
//        input.add(new InputNode(1));
//        input.add(new InputNode(0));
//        
//        input.add(new InputNode(1));
//        input.add(new InputNode(0));
//        input.add(new InputNode(1));
//        
//        input.add(new InputNode(1));
//        input.add(new InputNode(1));
//        input.add(new InputNode(0));
//        
//        List<TargetNode> target=new ArrayList<TargetNode>();
//        target.add(new TargetNode(0));
//        target.add(new TargetNode(0));
//        
//        List<HiddenNode> hidden=new ArrayList<HiddenNode>();
//        hidden.add(new HiddenNode(0,"Hidden 1"));
//        hidden.add(new HiddenNode(0,"Hidden 2"));
//        hidden.add(new HiddenNode(0,"Hidden 3"));
//        
//        List<OutputNode> output=new ArrayList<OutputNode>();
//        output.add(new OutputNode(0,"output 1"));
//        output.add(new OutputNode(0,"output 2"));
//
//        for(int i=0;i<output.size();i++){
//            output.get(i).setNodeTarget(target.get(i));
//        }
//        
//        List<Bobot> bobotToHidden= new ArrayList<Bobot>();
//        int x=0;
//        for(int i=0;i<input.size();i++){
//            for(int ii=0;ii<hidden.size();ii++){
//                bobotToHidden.add(new Bobot("bobotHideArray["+i+"] ["+ii+"]"));
//                bobotToHidden.get(x).setNodeInput(input.get(i));
//                bobotToHidden.get(x).setNodeTarget(hidden.get(ii));
//                //menambahkan bobot input untuk hidden layer dari layer input
//                hidden.get(ii).addBobotIn(bobotToHidden.get(x));
//                x++;
//                
//            }
//        }
//        
//        List<Bobot> bobotToOutput= new ArrayList<Bobot>();
//        x=0;
//        for(int i=0;i<hidden.size();i++){
//            for(int ii=0;ii<output.size();ii++){
//                bobotToOutput.add(new Bobot("bobotOutArray["+i+"] ["+ii+"]"));
//                bobotToOutput.get(x).setNodeInput(hidden.get(i));
//                bobotToOutput.get(x).setNodeTarget(output.get(ii));
//                //menambahkan bobotInput untuk output layer dari hiden layer
//                output.get(ii).addBobotIn(bobotToOutput.get(x));
//                //menambahkan output bobot pada hidden layer ke output layer
//                hidden.get(i).getBobotOut().add(bobotToOutput.get(x));
//                x++;
//            }
//        }
//        a=0;
//        boolean status=false;
//        
//        //---Pembelajaran-----------------------------------------------------------------------------------
//        while(!status){
//            status=true;
//            //setiap pasangan input
//            for(int in=0;in<inputArray.length;in++){
//                //inisialisasi pasangan input
//                for(int i=0;i<inputArray[in].length;i++){
//                    input.get(i).setNilai(inputArray[in][i]);
//                }
//                //inisialisasi pasangan target
//                for(int i=0;i<targetArray[in].length;i++){
//                    target.get(i).setNilai(targetArray[in][i]);
//                }
//                
//                //menghitung sinyal output dari layer input ke hidden layer
//                for(int i=0;i<hidden.size();i++){
//                    hidden.get(i).aktivasi();
//                }
//                //menghitung sinyal output dari hidden layer ke output layer
//                for(int i=0;i<output.size();i++){
//                    output.get(i).aktivasi();
//                }
//                a=0; 
//                //====================================================================
//                //pasang status kondisi
//                while(status && a<output.size()) {
////                    status=output.get(a++).tesNilaiAmbang();
//                    if(!output.get(a++).tesNilaiAmbang(in)){
//                        status=false;
//                    }
//                }
//                
//                //hitung error di lapisan output
//                for(int i=0;i<output.size();i++){
//                    output.get(i).hitungError();
//                    //System.err.println("error output :"+output.get(i).getError());
//                }
//                 //update bobot Wk
//                for (int i = 0; i < output.size(); i++) {
//                    output.get(i).updateBobotIn();
//                }
//                //hitung error di lapisan hidden
//                for(int i=0;i<hidden.size();i++){
//                    hidden.get(i).hitungError();
//                    //System.err.println("error input :"+hidden.get(i).getError());
//                }
//                //update bobot Vk
//                for(int i=0;i<hidden.size();i++){
//                    hidden.get(i).updateBobotIn();
//                }
//                
//            }
//                
//        }
//        //====MELIHAT BOBOT
//        for(int i=0;i<bobotToHidden.size();i++){
//                    System.out.println(bobotToHidden.get(i).getNamaBobot()+"="+bobotToHidden.get(i).getBobot()+";");
//                }
//        for(int i=0;i<bobotToOutput.size();i++){
//                    System.out.println(bobotToOutput.get(i).getNamaBobot()+"="+bobotToOutput.get(i).getBobot()+";");
//                }
//        int hasil;
//        int init;
//        //====TEST
//        for(int i=0;i<inputArray.length;i++){
//            System.out.print("Loop "+i);
//             //inisialisasi pasangan input
//                for(int ii=0;ii<inputArray[i].length;ii++){
//                    input.get(ii).setNilai(inputArray[i][ii]);
//                }
//                //aktivasi z
//                for(int ii=0;ii<hidden.size();ii++){
//                    hidden.get(ii).aktivasi();
//                }
//                for(int ii=0;ii<output.size();ii++){
//                    output.get(ii).aktivasi();
//                    System.out.print("["+output.get(ii).getNilaiBulat()+"]");
//                }
//                System.out.println("");
//                System.out.print("target ");
//                for(int ii=0;ii<output.size();ii++){
//                    System.out.print("["+targetArray[i][ii]+"]");
//                }
//                hasil=0;
//                init=2;
//                for(int ii=0;ii<output.size();ii++){
//                    hasil+=Math.pow(2,init--)*output.get(ii).getNilaiBulat();
//                }
//                System.out.println((char) (66+hasil));
//                
//        }
//    }

}
