package backpropagation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Bobot;
import model.HiddenNode;
import model.InputNode;
import model.OutputNode;
import model.TargetNode;

public class Aplikasi extends javax.swing.JFrame {

    private List<InputNode> input;
    private List<HiddenNode> hidden;
    private List<OutputNode> output;
    private List<Bobot> bobotHide;
    private List<Bobot> bobotOut;
    private int[][] inputArray;
    private int[][] targetArray;

    /**
     * Creates new form Aplikasi
     */
    public Aplikasi() {
        initComponents();
        txt_dataset.setEditable(false);
        initAwal();

    }

    public static void main(String[] args) {


        new Aplikasi().setVisible(true);


    }

    private void initAwal() {
        double bobotHideArray[][] = new double[15][3];
        //inisialisasi array dari pelatihan
        double bobotOutArray[][] = new double[3][2];
        //inisialisasi array dari pelatihan

        bobotHideArray[0][0] = -0.08585582973914523;
        bobotHideArray[0][1] = -0.10487796331669626;
        bobotHideArray[0][2] = 0.20212202499839468;
        bobotHideArray[1][0] = -0.028038438333990934;
        bobotHideArray[1][1] = 1.3814217704544998;
        bobotHideArray[1][2] = -0.736696863477271;
        bobotHideArray[2][0] = -1.8030957895135609;
        bobotHideArray[2][1] = -0.9291179925653447;
        bobotHideArray[2][2] = 2.2591787689441802;
        bobotHideArray[3][0] = -0.8009279414733258;
        bobotHideArray[3][1] = -0.4099226193129402;
        bobotHideArray[3][2] = -0.331657461092555;
        bobotHideArray[4][0] = -0.341906080261841;
        bobotHideArray[4][1] = 0.8168674356364343;
        bobotHideArray[4][2] = 0.3028467569756865;
        bobotHideArray[5][0] = 1.5029984535198961;
        bobotHideArray[5][1] = -0.918144421424437;
        bobotHideArray[5][2] = -0.6068393308470073;
        bobotHideArray[6][0] = -0.1832453153218451;
        bobotHideArray[6][1] = -1.0867796490399346;
        bobotHideArray[6][2] = 0.4925827133694498;
        bobotHideArray[7][0] = -1.8272286116771261;
        bobotHideArray[7][1] = 3.7735203120452354;
        bobotHideArray[7][2] = -0.0910815957674104;
        bobotHideArray[8][0] = -0.22551849913780664;
        bobotHideArray[8][1] = -0.9543664456689773;
        bobotHideArray[8][2] = -0.2569411060773061;
        bobotHideArray[9][0] = 0.4972056950580318;
        bobotHideArray[9][1] = 0.697671128079198;
        bobotHideArray[9][2] = -0.5144133231797278;
        bobotHideArray[10][0] = 0.5101402825925438;
        bobotHideArray[10][1] = -0.7023143557450986;
        bobotHideArray[10][2] = 0.9915010132556301;
        bobotHideArray[11][0] = 1.6101492403057103;
        bobotHideArray[11][1] = -1.479704439751193;
        bobotHideArray[11][2] = -0.5773371406819303;
        bobotHideArray[12][0] = 0.2975869269204363;
        bobotHideArray[12][1] = -0.040300865279675434;
        bobotHideArray[12][2] = 0.9347481418182256;
        bobotHideArray[13][0] = 0.19044845270324173;
        bobotHideArray[13][1] = 0.9068665959883887;
        bobotHideArray[13][2] = -1.0705879436032175;
        bobotHideArray[14][0] = -0.024226653609051744;
        bobotHideArray[14][1] = -1.6057743936492268;
        bobotHideArray[14][2] = 1.4910916231917364;
        bobotOutArray[0][0] = -3.618028000474793;
        bobotOutArray[0][1] = 2.8500549816430545;
        bobotOutArray[1][0] = -0.32545418324688585;
        bobotOutArray[1][1] = -5.2179768572389325;
        bobotOutArray[2][0] = 3.7793011953458833;
        bobotOutArray[2][1] = 2.0764914303291784;

        input = new ArrayList<InputNode>();
        for (int i = 0; i < 15; i++) {
            input.add(new InputNode(0));
        }
        hidden = new ArrayList<HiddenNode>();
        for (int i = 0; i < 3; i++) {
            hidden.add(new HiddenNode(0, "Hidden " + i));
        }
        output = new ArrayList<OutputNode>();
        for (int i = 0; i < 2; i++) {
            output.add(new OutputNode(0, "Output " + i));
        }
        //inisialisasi bobot to hidden
        int bobotCount = 0;
        bobotHide = new ArrayList<Bobot>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < hidden.size(); j++) {
                bobotHide.add(new Bobot(bobotHideArray[i][j], "bobotIn[" + i + "][" + j + "]"));
                bobotHide.get(bobotCount).setNodeInput(input.get(i));
                bobotHide.get(bobotCount).setNodeTarget(hidden.get(j));
                hidden.get(j).addBobotIn(bobotHide.get(bobotCount));
                bobotCount++;
            }
        }

        //inisialisasi bobot to out
        bobotCount = 0;
        bobotOut = new ArrayList<Bobot>();
        for (int j = 0; j < hidden.size(); j++) {
            for (int k = 0; k < output.size(); k++) {
                bobotOut.add(new Bobot(bobotOutArray[j][k], "bobotIn[" + j + "][" + k + "]"));
                bobotOut.get(bobotCount).setNodeInput(hidden.get(j));
                bobotOut.get(bobotCount).setNodeTarget(output.get(k));
                hidden.get(j).addBobotOut(bobotOut.get(bobotCount));
                output.get(k).addBobotIn(bobotOut.get(bobotCount));
                bobotCount++;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ed_target = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        button40 = new backpropagation.Button();
        button37 = new backpropagation.Button();
        button38 = new backpropagation.Button();
        button39 = new backpropagation.Button();
        button36 = new backpropagation.Button();
        button41 = new backpropagation.Button();
        button42 = new backpropagation.Button();
        button43 = new backpropagation.Button();
        button44 = new backpropagation.Button();
        button45 = new backpropagation.Button();
        button46 = new backpropagation.Button();
        button47 = new backpropagation.Button();
        button48 = new backpropagation.Button();
        button49 = new backpropagation.Button();
        button50 = new backpropagation.Button();
        button51 = new backpropagation.Button();
        button52 = new backpropagation.Button();
        button53 = new backpropagation.Button();
        button54 = new backpropagation.Button();
        button55 = new backpropagation.Button();
        button56 = new backpropagation.Button();
        button57 = new backpropagation.Button();
        button58 = new backpropagation.Button();
        button59 = new backpropagation.Button();
        button60 = new backpropagation.Button();
        button61 = new backpropagation.Button();
        button62 = new backpropagation.Button();
        button63 = new backpropagation.Button();
        button64 = new backpropagation.Button();
        button65 = new backpropagation.Button();
        button66 = new backpropagation.Button();
        button67 = new backpropagation.Button();
        button68 = new backpropagation.Button();
        button69 = new backpropagation.Button();
        button70 = new backpropagation.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_dataset = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_load = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        button1 = new backpropagation.Button();
        button2 = new backpropagation.Button();
        button3 = new backpropagation.Button();
        button4 = new backpropagation.Button();
        button5 = new backpropagation.Button();
        button6 = new backpropagation.Button();
        button7 = new backpropagation.Button();
        button8 = new backpropagation.Button();
        button9 = new backpropagation.Button();
        button10 = new backpropagation.Button();
        button11 = new backpropagation.Button();
        button12 = new backpropagation.Button();
        button13 = new backpropagation.Button();
        button14 = new backpropagation.Button();
        button15 = new backpropagation.Button();
        button16 = new backpropagation.Button();
        button17 = new backpropagation.Button();
        button18 = new backpropagation.Button();
        button19 = new backpropagation.Button();
        button20 = new backpropagation.Button();
        button21 = new backpropagation.Button();
        button22 = new backpropagation.Button();
        button23 = new backpropagation.Button();
        button24 = new backpropagation.Button();
        button25 = new backpropagation.Button();
        button26 = new backpropagation.Button();
        button27 = new backpropagation.Button();
        button28 = new backpropagation.Button();
        button29 = new backpropagation.Button();
        button30 = new backpropagation.Button();
        button31 = new backpropagation.Button();
        button32 = new backpropagation.Button();
        button33 = new backpropagation.Button();
        button34 = new backpropagation.Button();
        button35 = new backpropagation.Button();
        jPanel2 = new javax.swing.JPanel();
        outputLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pengenalan Huruf-Backpropagation");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));

        jLabel1.setText("Target Huruf");

        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        button40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button40ActionPerformed(evt);
            }
        });

        button37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button37ActionPerformed(evt);
            }
        });

        button38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button38ActionPerformed(evt);
            }
        });

        button39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button39ActionPerformed(evt);
            }
        });

        button36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button36ActionPerformed(evt);
            }
        });

        button41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button41ActionPerformed(evt);
            }
        });

        button42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button42ActionPerformed(evt);
            }
        });

        button43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button43ActionPerformed(evt);
            }
        });

        button44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button44ActionPerformed(evt);
            }
        });

        button45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button45ActionPerformed(evt);
            }
        });

        button46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button46ActionPerformed(evt);
            }
        });

        button47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button47ActionPerformed(evt);
            }
        });

        button48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button48ActionPerformed(evt);
            }
        });

        button49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button49ActionPerformed(evt);
            }
        });

        button50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button50ActionPerformed(evt);
            }
        });

        button51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button51ActionPerformed(evt);
            }
        });

        button52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button52ActionPerformed(evt);
            }
        });

        button53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button53ActionPerformed(evt);
            }
        });

        button54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button54ActionPerformed(evt);
            }
        });

        button55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button55ActionPerformed(evt);
            }
        });

        button56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button56ActionPerformed(evt);
            }
        });

        button57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button57ActionPerformed(evt);
            }
        });

        button58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button58ActionPerformed(evt);
            }
        });

        button59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button59ActionPerformed(evt);
            }
        });

        button60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button60ActionPerformed(evt);
            }
        });

        button61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button61ActionPerformed(evt);
            }
        });

        button62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button62ActionPerformed(evt);
            }
        });

        button63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button63ActionPerformed(evt);
            }
        });

        button64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button64ActionPerformed(evt);
            }
        });

        button65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button65ActionPerformed(evt);
            }
        });

        button66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button66ActionPerformed(evt);
            }
        });

        button67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button67ActionPerformed(evt);
            }
        });

        button68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button68ActionPerformed(evt);
            }
        });

        button69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button69ActionPerformed(evt);
            }
        });

        button70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button70ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_target, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_target, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        txt_dataset.setColumns(20);
        txt_dataset.setRows(5);
        jScrollPane1.setViewportView(txt_dataset);

        jButton3.setText("Train");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Dataset");

        jButton4.setText("Clear Dataset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btn_save.setText("Save Dataset");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_load.setText("Load Dataset");
        btn_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_save)
                        .addGap(18, 18, 18)
                        .addComponent(btn_load)))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_save)
                            .addComponent(btn_load))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Learning", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));

        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });

        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });

        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });

        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button16ActionPerformed(evt);
            }
        });

        button17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button17ActionPerformed(evt);
            }
        });

        button18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button18ActionPerformed(evt);
            }
        });

        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });

        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });

        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });

        button22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button22ActionPerformed(evt);
            }
        });

        button23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button23ActionPerformed(evt);
            }
        });

        button24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button24ActionPerformed(evt);
            }
        });

        button25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button25ActionPerformed(evt);
            }
        });

        button26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button26ActionPerformed(evt);
            }
        });

        button27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button27ActionPerformed(evt);
            }
        });

        button28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button28ActionPerformed(evt);
            }
        });

        button29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button29ActionPerformed(evt);
            }
        });

        button30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button30ActionPerformed(evt);
            }
        });

        button31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button31ActionPerformed(evt);
            }
        });

        button32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button32ActionPerformed(evt);
            }
        });

        button33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button33ActionPerformed(evt);
            }
        });

        button34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button34ActionPerformed(evt);
            }
        });

        button35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        outputLabel.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        outputLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(outputLabel, java.awt.BorderLayout.CENTER);

        jButton1.setText("Baca");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(42, 42, 42))))
        );

        jTabbedPane1.addTab("Test", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        button1.tekanButton();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        button2.tekanButton();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        button3.tekanButton();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        button4.tekanButton();
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        button5.tekanButton();
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        button6.tekanButton();
    }//GEN-LAST:event_button6ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        button7.tekanButton();
    }//GEN-LAST:event_button7ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        button8.tekanButton();
    }//GEN-LAST:event_button8ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        button9.tekanButton();
    }//GEN-LAST:event_button9ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        button10.tekanButton();
    }//GEN-LAST:event_button10ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        button11.tekanButton();
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        button12.tekanButton();
    }//GEN-LAST:event_button12ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        button13.tekanButton();
    }//GEN-LAST:event_button13ActionPerformed

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button14ActionPerformed
        button14.tekanButton();
    }//GEN-LAST:event_button14ActionPerformed

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button15ActionPerformed
        button15.tekanButton();
    }//GEN-LAST:event_button15ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        input.get(0).setNilai(button1.getNilai());
        input.get(1).setNilai(button2.getNilai());
        input.get(2).setNilai(button3.getNilai());
        input.get(3).setNilai(button4.getNilai());
        input.get(4).setNilai(button5.getNilai());
        input.get(5).setNilai(button6.getNilai());
        input.get(6).setNilai(button7.getNilai());
        input.get(7).setNilai(button8.getNilai());
        input.get(8).setNilai(button9.getNilai());
        input.get(9).setNilai(button10.getNilai());
        input.get(10).setNilai(button11.getNilai());
        input.get(11).setNilai(button12.getNilai());
        input.get(12).setNilai(button13.getNilai());
        input.get(13).setNilai(button14.getNilai());
        input.get(14).setNilai(button15.getNilai());
        input.get(15).setNilai(button16.getNilai());
        input.get(16).setNilai(button17.getNilai());
        input.get(17).setNilai(button18.getNilai());
        input.get(18).setNilai(button19.getNilai());
        input.get(19).setNilai(button20.getNilai());
        input.get(20).setNilai(button21.getNilai());
        input.get(21).setNilai(button22.getNilai());
        input.get(22).setNilai(button23.getNilai());
        input.get(23).setNilai(button24.getNilai());
        input.get(24).setNilai(button25.getNilai());
        input.get(25).setNilai(button26.getNilai());
        input.get(26).setNilai(button27.getNilai());
        input.get(27).setNilai(button28.getNilai());
        input.get(28).setNilai(button29.getNilai());
        input.get(29).setNilai(button30.getNilai());
        input.get(30).setNilai(button31.getNilai());
        input.get(31).setNilai(button32.getNilai());
        input.get(32).setNilai(button33.getNilai());
        input.get(33).setNilai(button34.getNilai());
        input.get(34).setNilai(button35.getNilai());

        for (int i = 0; i < hidden.size(); i++) {
            hidden.get(i).aktivasi();
        }
        int hasil = 0;
        int init = 4;
        for (int i = 0; i < output.size(); i++) {
            output.get(i).aktivasi();
            hasil += Math.pow(2, init) * output.get(i).getNilaiBulat();
            init--;
            System.out.print("[" + output.get(i).getNilaiBulat() + "]");
        }

        char value = (char) (64 + hasil);
        outputLabel.setText(String.valueOf(value));
        System.out.println(hasil);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void button16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button16ActionPerformed
        // TODO add your handling code here:
        button16.tekanButton();
    }//GEN-LAST:event_button16ActionPerformed

    private void button17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button17ActionPerformed
        // TODO add your handling code here:
        button17.tekanButton();
    }//GEN-LAST:event_button17ActionPerformed

    private void button18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button18ActionPerformed
        // TODO add your handling code here:
        button18.tekanButton();
    }//GEN-LAST:event_button18ActionPerformed

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button19ActionPerformed
        // TODO add your handling code here:
        button19.tekanButton();
    }//GEN-LAST:event_button19ActionPerformed

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button20ActionPerformed
        // TODO add your handling code here:
        button20.tekanButton();
    }//GEN-LAST:event_button20ActionPerformed

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button21ActionPerformed
        // TODO add your handling code here:
        button21.tekanButton();
    }//GEN-LAST:event_button21ActionPerformed

    private void button22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button22ActionPerformed
        // TODO add your handling code here:
        button22.tekanButton();
    }//GEN-LAST:event_button22ActionPerformed

    private void button23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button23ActionPerformed
        // TODO add your handling code here:
        button23.tekanButton();
    }//GEN-LAST:event_button23ActionPerformed

    private void button24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button24ActionPerformed
        // TODO add your handling code here:
        button24.tekanButton();
    }//GEN-LAST:event_button24ActionPerformed

    private void button25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button25ActionPerformed
        // TODO add your handling code here:
        button25.tekanButton();
    }//GEN-LAST:event_button25ActionPerformed

    private void button26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button26ActionPerformed
        // TODO add your handling code here:
        button26.tekanButton();
    }//GEN-LAST:event_button26ActionPerformed

    private void button27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button27ActionPerformed
        // TODO add your handling code here:
        button27.tekanButton();
    }//GEN-LAST:event_button27ActionPerformed

    private void button28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button28ActionPerformed
        // TODO add your handling code here:
        button28.tekanButton();
    }//GEN-LAST:event_button28ActionPerformed

    private void button29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button29ActionPerformed
        // TODO add your handling code here:
        button29.tekanButton();
    }//GEN-LAST:event_button29ActionPerformed

    private void button30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button30ActionPerformed
        // TODO add your handling code here:
        button30.tekanButton();
    }//GEN-LAST:event_button30ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (ed_target.getText().equals("")) {
                throw new IllegalArgumentException("Target Huruf belum Ada");
            }

            String data = "";
            data += button36.getNilai();
            data += button37.getNilai();
            data += button38.getNilai();
            data += button39.getNilai();
            data += button40.getNilai();
            data += button41.getNilai();
            data += button42.getNilai();
            data += button43.getNilai();
            data += button44.getNilai();
            data += button45.getNilai();
            data += button46.getNilai();
            data += button47.getNilai();
            data += button48.getNilai();
            data += button49.getNilai();
            data += button50.getNilai();
            data += button51.getNilai();
            data += button52.getNilai();
            data += button53.getNilai();
            data += button54.getNilai();
            data += button55.getNilai();
            data += button56.getNilai();
            data += button57.getNilai();
            data += button58.getNilai();
            data += button59.getNilai();
            data += button60.getNilai();
            data += button61.getNilai();
            data += button62.getNilai();
            data += button63.getNilai();
            data += button64.getNilai();
            data += button65.getNilai();
            data += button66.getNilai();
            data += button67.getNilai();
            data += button68.getNilai();
            data += button69.getNilai();
            data += button70.getNilai();
            data += " " + ed_target.getText() + "\n";
            txt_dataset.append(data);

            this.inputArray = getInputArray(txt_dataset.getText());
            this.targetArray = getTargetArray(txt_dataset.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //int Input[][]=
        int a = 0;
//            int banyakInput=4;
//        int [][] inputArray=new int[banyakInput][];int [][] targetArray=new int[banyakInput][];
//      inputArray[a]=new int[]{1,1,0,1,0,1,1,1,1,1,0,1,1,1,0}; targetArray[a++]=new int[]{0,0};//B
//      inputArray[a]=new int[]{1,1,0,1,0,1,1,0,1,1,0,1,1,1,0}; targetArray[a++]=new int[]{0,1};//D
//      inputArray[a]=new int[]{1,1,1,1,0,0,1,1,0,1,0,0,1,0,0}; targetArray[a++]=new int[]{1,0};//F
//      inputArray[a]=new int[]{1,0,1,1,0,1,1,1,1,1,0,1,1,0,1}; targetArray[a++]=new int[]{1,1};//H

        this.input = new ArrayList<InputNode>();
        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(0));
        input.add(new InputNode(1));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(0));
        input.add(new InputNode(1));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(0));
        input.add(new InputNode(1));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(0));
        input.add(new InputNode(1));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(1));
        input.add(new InputNode(0));

        input.add(new InputNode(1));
        input.add(new InputNode(1));




        List<TargetNode> target = new ArrayList<TargetNode>();
        target.add(new TargetNode(0));
        target.add(new TargetNode(0));
        target.add(new TargetNode(0));
        target.add(new TargetNode(0));
        target.add(new TargetNode(0));

        this.hidden = new ArrayList<HiddenNode>();
        hidden.add(new HiddenNode(0, "Hidden 1"));
        hidden.add(new HiddenNode(0, "Hidden 2"));
        hidden.add(new HiddenNode(0, "Hidden 3"));
        hidden.add(new HiddenNode(0, "Hidden 4"));
        hidden.add(new HiddenNode(0, "Hidden 5"));
        hidden.add(new HiddenNode(0, "Hidden 6"));
        hidden.add(new HiddenNode(0, "Hidden 7"));
        hidden.add(new HiddenNode(0, "Hidden 8"));
        hidden.add(new HiddenNode(0, "Hidden 9"));
        hidden.add(new HiddenNode(0, "Hidden 10"));
        hidden.add(new HiddenNode(0, "Hidden 11"));
        hidden.add(new HiddenNode(0, "Hidden 12"));
        hidden.add(new HiddenNode(0, "Hidden 13"));
        hidden.add(new HiddenNode(0, "Hidden 14"));
        hidden.add(new HiddenNode(0, "Hidden 15"));
        hidden.add(new HiddenNode(0, "Hidden 16"));
        hidden.add(new HiddenNode(0, "Hidden 17"));
        hidden.add(new HiddenNode(0, "Hidden 18"));
        hidden.add(new HiddenNode(0, "Hidden 19"));
        hidden.add(new HiddenNode(0, "Hidden 20"));

        this.output = new ArrayList<OutputNode>();
        output.add(new OutputNode(0, "output 1"));
        output.add(new OutputNode(0, "output 2"));
        output.add(new OutputNode(0, "output 3"));
        output.add(new OutputNode(0, "output 4"));
        output.add(new OutputNode(0, "output 5"));

        for (int i = 0; i < output.size(); i++) {
            output.get(i).setNodeTarget(target.get(i));
        }

        this.bobotHide = new ArrayList<Bobot>();
        int x = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int ii = 0; ii < hidden.size(); ii++) {
                bobotHide.add(new Bobot("bobotHideArray[" + i + "] [" + ii + "]"));
                bobotHide.get(x).setNodeInput(input.get(i));
                bobotHide.get(x).setNodeTarget(hidden.get(ii));
                //menambahkan bobot input untuk hidden layer dari layer input
                hidden.get(ii).addBobotIn(bobotHide.get(x));
                x++;

            }
        }

        this.bobotOut = new ArrayList<Bobot>();
        x = 0;
        for (int i = 0; i < hidden.size(); i++) {
            for (int ii = 0; ii < output.size(); ii++) {
                bobotOut.add(new Bobot("bobotOutArray[" + i + "] [" + ii + "]"));
                bobotOut.get(x).setNodeInput(hidden.get(i));
                bobotOut.get(x).setNodeTarget(output.get(ii));
                //menambahkan bobotInput untuk output layer dari hiden layer
                output.get(ii).addBobotIn(bobotOut.get(x));
                //menambahkan output bobot pada hidden layer ke output layer
                hidden.get(i).getBobotOut().add(bobotOut.get(x));
                x++;
            }
        }
        a = 0;
        boolean status = false;

        //---Pembelajaran-----------------------------------------------------------------------------------
        while (!status) {
            status = true;
            //setiap pasangan input
            for (int in = 0; in < inputArray.length; in++) {
                //inisialisasi pasangan input
                for (int i = 0; i < inputArray[in].length; i++) {
                    input.get(i).setNilai(inputArray[in][i]);
                }
                //inisialisasi pasangan target
                for (int i = 0; i < targetArray[in].length; i++) {
                    target.get(i).setNilai(targetArray[in][i]);
                }

                //menghitung sinyal output dari layer input ke hidden layer
                for (int i = 0; i < hidden.size(); i++) {
                    hidden.get(i).aktivasi();
                }
                //menghitung sinyal output dari hidden layer ke output layer
                for (int i = 0; i < output.size(); i++) {
                    output.get(i).aktivasi();
                }
                a = 0;
                //====================================================================
                //pasang status kondisi
                while (status && a < output.size()) {
//                    status=output.get(a++).tesNilaiAmbang();
                    if (!output.get(a++).tesNilaiAmbang(in)) {
                        status = false;
                    }
                }

                //hitung error di lapisan output
                for (int i = 0; i < output.size(); i++) {
                    output.get(i).hitungError();
                    //System.err.println("error output :"+output.get(i).getError());
                }
                //update bobot Wk
                for (int i = 0; i < output.size(); i++) {
                    output.get(i).updateBobotIn();
                }
                //hitung error di lapisan hidden
                for (int i = 0; i < hidden.size(); i++) {
                    hidden.get(i).hitungError();
                    //System.err.println("error input :"+hidden.get(i).getError());
                }
                //update bobot Vk
                for (int i = 0; i < hidden.size(); i++) {
                    hidden.get(i).updateBobotIn();
                }

            }

        }
        //====MELIHAT BOBOT
        for (int i = 0; i < bobotHide.size(); i++) {
            System.out.println(bobotHide.get(i).getNamaBobot() + "=" + bobotHide.get(i).getBobot() + ";");
        }
        for (int i = 0; i < bobotOut.size(); i++) {
            System.out.println(bobotOut.get(i).getNamaBobot() + "=" + bobotOut.get(i).getBobot() + ";");
        }
        int hasil;
        int init;
        //====TEST
        for (int i = 0; i < inputArray.length; i++) {
            System.out.print("Loop " + i);
            //inisialisasi pasangan input
            for (int ii = 0; ii < inputArray[i].length; ii++) {
                input.get(ii).setNilai(inputArray[i][ii]);
            }
            //aktivasi z
            for (int ii = 0; ii < hidden.size(); ii++) {
                hidden.get(ii).aktivasi();
            }
            for (int ii = 0; ii < output.size(); ii++) {
                output.get(ii).aktivasi();
                System.out.print("[" + output.get(ii).getNilaiBulat() + "]");
            }
            System.out.println("");
            System.out.print("target ");
            for (int ii = 0; ii < output.size(); ii++) {
                System.out.print("[" + targetArray[i][ii] + "]");
            }
            hasil = 0;
            init = 4;
            for (int ii = 0; ii < output.size(); ii++) {
                hasil += Math.pow(2, init--) * output.get(ii).getNilaiBulat();
            }
            System.out.println((char) (65 + hasil));

        }

        JOptionPane.showMessageDialog(this, "Training Complete");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_dataset.setText("");
        this.inputArray = null;
        this.targetArray = null;
    }//GEN-LAST:event_jButton4ActionPerformed

    public void RegisterBtn(final Button btn) {


        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn.tekanButton();
            }
        });

    }

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            String path = file.getSelectedFile().getAbsolutePath();
            String result = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String sCurrentLine;


                while ((sCurrentLine = br.readLine()) != null) {
                    result += sCurrentLine + "\n";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            txt_dataset.setText(result);
            this.inputArray = getInputArray(txt_dataset.getText());
            this.targetArray = getTargetArray(txt_dataset.getText());
        }

    }//GEN-LAST:event_btn_loadActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        JFileChooser fl = new JFileChooser();

        if (fl.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            String name = fl.getSelectedFile().getAbsolutePath();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(name + ".txt"));

                bw.write(txt_dataset.getText());
                System.out.println("sdfdf");
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void button31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button31ActionPerformed
        // TODO add your handling code here:
        button31.tekanButton();
    }//GEN-LAST:event_button31ActionPerformed

    private void button32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button32ActionPerformed
        // TODO add your handling code here:
        button32.tekanButton();
    }//GEN-LAST:event_button32ActionPerformed

    private void button33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button33ActionPerformed
        // TODO add your handling code here:
        button33.tekanButton();
    }//GEN-LAST:event_button33ActionPerformed

    private void button34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button34ActionPerformed
        // TODO add your handling code here:
        button34.tekanButton();
    }//GEN-LAST:event_button34ActionPerformed

    private void button35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button35ActionPerformed
        // TODO add your handling code here:
        button35.tekanButton();
    }//GEN-LAST:event_button35ActionPerformed

    private void button36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button36ActionPerformed
        // TODO add your handling code here:
        button36.tekanButton();
    }//GEN-LAST:event_button36ActionPerformed

    private void button37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button37ActionPerformed
        // TODO add your handling code here:
        button37.tekanButton();
    }//GEN-LAST:event_button37ActionPerformed

    private void button38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button38ActionPerformed
        // TODO add your handling code here:
        button38.tekanButton();
    }//GEN-LAST:event_button38ActionPerformed

    private void button39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button39ActionPerformed
        // TODO add your handling code here:
        button39.tekanButton();
    }//GEN-LAST:event_button39ActionPerformed

    private void button40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button40ActionPerformed
        // TODO add your handling code here:
        button40.tekanButton();
    }//GEN-LAST:event_button40ActionPerformed

    private void button41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button41ActionPerformed
        // TODO add your handling code here:
        button41.tekanButton();
    }//GEN-LAST:event_button41ActionPerformed

    private void button42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button42ActionPerformed
        // TODO add your handling code here:
        button42.tekanButton();
    }//GEN-LAST:event_button42ActionPerformed

    private void button43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button43ActionPerformed
        // TODO add your handling code here:
        button43.tekanButton();
    }//GEN-LAST:event_button43ActionPerformed

    private void button44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button44ActionPerformed
        // TODO add your handling code here:
        button44.tekanButton();
    }//GEN-LAST:event_button44ActionPerformed

    private void button45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button45ActionPerformed
        // TODO add your handling code here:
        button45.tekanButton();
    }//GEN-LAST:event_button45ActionPerformed

    private void button46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button46ActionPerformed
        // TODO add your handling code here:
        button46.tekanButton();
    }//GEN-LAST:event_button46ActionPerformed

    private void button47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button47ActionPerformed
        // TODO add your handling code here:
        button47.tekanButton();
    }//GEN-LAST:event_button47ActionPerformed

    private void button48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button48ActionPerformed
        // TODO add your handling code here:
        button48.tekanButton();
    }//GEN-LAST:event_button48ActionPerformed

    private void button49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button49ActionPerformed
        // TODO add your handling code here:
        button49.tekanButton();
    }//GEN-LAST:event_button49ActionPerformed

    private void button50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button50ActionPerformed
        // TODO add your handling code here:
        button50.tekanButton();
    }//GEN-LAST:event_button50ActionPerformed

    private void button51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button51ActionPerformed
        // TODO add your handling code here:
        button51.tekanButton();
    }//GEN-LAST:event_button51ActionPerformed

    private void button52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button52ActionPerformed
        // TODO add your handling code here:
        button52.tekanButton();
    }//GEN-LAST:event_button52ActionPerformed

    private void button53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button53ActionPerformed
        // TODO add your handling code here:
        button53.tekanButton();
    }//GEN-LAST:event_button53ActionPerformed

    private void button54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button54ActionPerformed
        // TODO add your handling code here:
        button54.tekanButton();
    }//GEN-LAST:event_button54ActionPerformed

    private void button55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button55ActionPerformed
        // TODO add your handling code here:
        button55.tekanButton();
    }//GEN-LAST:event_button55ActionPerformed

    private void button56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button56ActionPerformed
        // TODO add your handling code here:
        button56.tekanButton();
    }//GEN-LAST:event_button56ActionPerformed

    private void button57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button57ActionPerformed
        // TODO add your handling code here:
        button57.tekanButton();
    }//GEN-LAST:event_button57ActionPerformed

    private void button58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button58ActionPerformed
        // TODO add your handling code here:
        button58.tekanButton();
    }//GEN-LAST:event_button58ActionPerformed

    private void button59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button59ActionPerformed
        // TODO add your handling code here:
        button59.tekanButton();
    }//GEN-LAST:event_button59ActionPerformed

    private void button60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button60ActionPerformed
        // TODO add your handling code here:
        button60.tekanButton();
    }//GEN-LAST:event_button60ActionPerformed

    private void button61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button61ActionPerformed
        // TODO add your handling code here:
        button61.tekanButton();
    }//GEN-LAST:event_button61ActionPerformed

    private void button62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button62ActionPerformed
        // TODO add your handling code here:
        button62.tekanButton();
    }//GEN-LAST:event_button62ActionPerformed

    private void button63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button63ActionPerformed
        // TODO add your handling code here:
        button63.tekanButton();
    }//GEN-LAST:event_button63ActionPerformed

    private void button64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button64ActionPerformed
        // TODO add your handling code here:
        button64.tekanButton();
    }//GEN-LAST:event_button64ActionPerformed

    private void button65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button65ActionPerformed
        // TODO add your handling code here:
        button65.tekanButton();
    }//GEN-LAST:event_button65ActionPerformed

    private void button66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button66ActionPerformed
        // TODO add your handling code here:
        button66.tekanButton();
    }//GEN-LAST:event_button66ActionPerformed

    private void button67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button67ActionPerformed
        // TODO add your handling code here:
        button67.tekanButton();
    }//GEN-LAST:event_button67ActionPerformed

    private void button68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button68ActionPerformed
        // TODO add your handling code here:
        button68.tekanButton();
    }//GEN-LAST:event_button68ActionPerformed

    private void button69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button69ActionPerformed
        // TODO add your handling code here:
        button69.tekanButton();
    }//GEN-LAST:event_button69ActionPerformed

    private void button70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button70ActionPerformed
        // TODO add your handling code here:
        button70.tekanButton();
    }//GEN-LAST:event_button70ActionPerformed

    /**
     * @param args the command line arguments
     */
    private int[] getTarget(String data) {
        String target = data.split(" ")[1];
        if (target.equals("A")) {
            return new int[]{0, 0, 0, 0, 1};
        } else if (target.equals("B")) {
            return new int[]{0, 0, 0, 1, 0};
        } else if (target.equals("C")) {
            return new int[]{0, 0, 0, 1, 1};
        } else if (target.equals("D")) {
            return new int[]{0, 0, 1, 0, 0};
        } else if (target.equals("E")) {
            return new int[]{0, 0, 1, 0, 1};
        } else if (target.equals("F")) {
            return new int[]{0, 0, 1, 1, 0};
        } else if (target.equals("G")) {
            return new int[]{0, 0, 1, 1, 1};
        } else if (target.equals("H")) {
            return new int[]{0, 1, 0, 0, 0};
        } else if (target.equals("I")) {
            return new int[]{0, 1, 0, 0, 1};
        } else if (target.equals("J")) {
            return new int[]{0, 1, 0, 1, 0};
        } else if (target.equals("K")) {
            return new int[]{0, 1, 0, 1, 1};
        } else if (target.equals("L")) {
            return new int[]{0, 1, 1, 0, 0};
        } else if (target.equals("M")) {
            return new int[]{0, 1, 1, 0, 1};
        } else if (target.equals("N")) {
            return new int[]{0, 1, 1, 1, 0};
        } else if (target.equals("O")) {
            return new int[]{0, 1, 1, 1, 1};
        } else if (target.equals("P")) {
            return new int[]{1, 0, 0, 0, 0};
        } else if (target.equals("Q")) {
            return new int[]{1, 0, 0, 0, 1};
        } else if (target.equals("R")) {
            return new int[]{1, 0, 0, 1, 0};
        } else if (target.equals("S")) {
            return new int[]{1, 0, 0, 1, 1};
        } else if (target.equals("T")) {
            return new int[]{1, 0, 1, 0, 0};
        } else if (target.equals("U")) {
            return new int[]{1, 0, 1, 0, 1};
        } else if (target.equals("V")) {
            return new int[]{1, 0, 1, 1, 0};
        } else if (target.equals("W")) {
            return new int[]{1, 0, 1, 1, 1};
        } else if (target.equals("X")) {
            return new int[]{1, 1, 0, 0, 0};
        } else if (target.equals("Y")) {
            return new int[]{1, 1, 0, 0, 1};
        } else if (target.equals("Z")) {
            return new int[]{1, 1, 0, 1, 0};
        }

        return new int[]{0, 0};

    }

    private int[] getFeature(String data) {
        String target = data.split(" ")[0];
        int[] feature = new int[target.length()];
        for (int i = 0; i < target.length(); i++) {
            feature[i] = Integer.parseInt(String.valueOf(target.charAt(i)));
        }
        return feature;
    }

    private int[][] getInputArray(String data) {
        String[] dataset = data.split("\n");
        int[][] inputArray = new int[dataset.length][];
        for (int i = 0; i < dataset.length; i++) {
            inputArray[i] = getFeature(dataset[i]);

        }
        return inputArray;
    }

    private int[][] getTargetArray(String data) {
        String[] dataset = data.split("\n");
        int[][] targetArray = new int[dataset.length][];
        for (int i = 0; i < dataset.length; i++) {
            targetArray[i] = getTarget(dataset[i]);

        }
        return targetArray;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_load;
    private javax.swing.JButton btn_save;
    private backpropagation.Button button1;
    private backpropagation.Button button10;
    private backpropagation.Button button11;
    private backpropagation.Button button12;
    private backpropagation.Button button13;
    private backpropagation.Button button14;
    private backpropagation.Button button15;
    private backpropagation.Button button16;
    private backpropagation.Button button17;
    private backpropagation.Button button18;
    private backpropagation.Button button19;
    private backpropagation.Button button2;
    private backpropagation.Button button20;
    private backpropagation.Button button21;
    private backpropagation.Button button22;
    private backpropagation.Button button23;
    private backpropagation.Button button24;
    private backpropagation.Button button25;
    private backpropagation.Button button26;
    private backpropagation.Button button27;
    private backpropagation.Button button28;
    private backpropagation.Button button29;
    private backpropagation.Button button3;
    private backpropagation.Button button30;
    private backpropagation.Button button31;
    private backpropagation.Button button32;
    private backpropagation.Button button33;
    private backpropagation.Button button34;
    private backpropagation.Button button35;
    private backpropagation.Button button36;
    private backpropagation.Button button37;
    private backpropagation.Button button38;
    private backpropagation.Button button39;
    private backpropagation.Button button4;
    private backpropagation.Button button40;
    private backpropagation.Button button41;
    private backpropagation.Button button42;
    private backpropagation.Button button43;
    private backpropagation.Button button44;
    private backpropagation.Button button45;
    private backpropagation.Button button46;
    private backpropagation.Button button47;
    private backpropagation.Button button48;
    private backpropagation.Button button49;
    private backpropagation.Button button5;
    private backpropagation.Button button50;
    private backpropagation.Button button51;
    private backpropagation.Button button52;
    private backpropagation.Button button53;
    private backpropagation.Button button54;
    private backpropagation.Button button55;
    private backpropagation.Button button56;
    private backpropagation.Button button57;
    private backpropagation.Button button58;
    private backpropagation.Button button59;
    private backpropagation.Button button6;
    private backpropagation.Button button60;
    private backpropagation.Button button61;
    private backpropagation.Button button62;
    private backpropagation.Button button63;
    private backpropagation.Button button64;
    private backpropagation.Button button65;
    private backpropagation.Button button66;
    private backpropagation.Button button67;
    private backpropagation.Button button68;
    private backpropagation.Button button69;
    private backpropagation.Button button7;
    private backpropagation.Button button70;
    private backpropagation.Button button8;
    private backpropagation.Button button9;
    private javax.swing.JTextField ed_target;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea txt_dataset;
    // End of variables declaration//GEN-END:variables
}
