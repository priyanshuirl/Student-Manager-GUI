import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.GridLayout;

public class ClassListSystem {
    public static void main(String[] args) {
        // Instantiating the Frame for Home Menu
        class Frame extends JFrame {
            public Frame() {
                setLayout(new FlowLayout(FlowLayout.LEFT));
                this.setSize(600, 600);
                this.getContentPane().setBackground(new Color(0x222222));
                setLocationRelativeTo(null);
                this.setResizable(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
        Frame home = new Frame();
        home.setTitle("Student Manager");

        // Creating the Menu and Menu Items
        JMenu menu;
        JMenuItem op1, op2, op3, op4, op5, op6, op7;
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Commands Menu");
        op1 = new JMenuItem("Enter info about a new GraduateStudent");
        op2 = new JMenuItem("Print out all student info");
        op3 = new JMenuItem("Print average of student averages, as well as total number of students");
        op4 = new JMenuItem("Read input file");
        op5 = new JMenuItem("File Data output");
        op6 = new JMenuItem("Lookup via a HashMap key");
        op7 = new JMenuItem("End program");
        menu.add(op1);
        menu.add(op2);
        menu.add(op3);
        menu.add(op4);
        menu.add(op5);
        menu.add(op6);
        menu.add(op7);

        // Adding styles to the Menu and Menu Items
        op1.setFont(new Font("Arial", Font.BOLD, 16));
        op2.setFont(new Font("Arial", Font.BOLD, 16));
        op3.setFont(new Font("Arial", Font.BOLD, 16));
        op4.setFont(new Font("Arial", Font.BOLD, 16));
        op5.setFont(new Font("Arial", Font.BOLD, 16));
        op6.setFont(new Font("Arial", Font.BOLD, 16));
        op7.setFont(new Font("Arial", Font.BOLD, 16));

        mb.add(menu);
        home.setJMenuBar(mb);

        Font f = new FontUIResource(mb.getFont().getFontName(), mb.getFont().getStyle(), 30);
        UIManager.put("Menu.font", f);
        SwingUtilities.updateComponentTreeUI(home);
        mb.setOpaque(true);
        mb.setBackground(new Color(0xc700ff));
        menu.setForeground(new Color(0xffffff));
        Border emptyBorder3 = new EmptyBorder(10, 10, 10, 10);
        mb.setBorder(emptyBorder3);

        // Initialising the frames
        OPFrame enterinfostud = new OPFrame();
        OPFrame enterinfogradstud = new OPFrame();
        OPFrame printinfo = new OPFrame();
        OPFrame printavg = new OPFrame();
        OPFrame inputfile = new OPFrame();
        OPFrame outputfile = new OPFrame();
        OPFrame hashmapsearch = new OPFrame();

        op1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                enterinfostud.getContentPane().removeAll();
                enterinfostud.repaint();
                enterinfostud.setTitle("Enter Student Info");
                enterinfostud.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Enter New Student Info");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 21));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                // Input for Program
                JLabel program = new JLabel("Program : ");
                program.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                program.setForeground(new Color(0xffffff));
                inputs.add(program);
                JTextField cb = new JTextField(10);
                cb.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(cb);

                // Input for Year
                JLabel year = new JLabel("Year : ");
                year.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                year.setForeground(new Color(0xffffff));
                inputs.add(year);
                JTextField t1 = new JTextField(10);
                t1.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t1);

                // Input for Average Grade
                JLabel avggrade = new JLabel("Average Grade : ");
                avggrade.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                avggrade.setForeground(new Color(0xffffff));
                inputs.add(avggrade);
                JTextField t2 = new JTextField(5);
                t2.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t2);

                // Input for Last Name
                JLabel lname = new JLabel("Last Name : ");
                lname.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                lname.setForeground(new Color(0xffffff));
                inputs.add(lname);
                JTextField t3 = new JTextField(6);
                t3.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t3);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Add");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);
                // display.setBackground(new Color(0xc700ff));
                // display.setForeground(new Color(0xffffff));

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);
                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String program = cb.getText().toLowerCase();
                            int year = Integer.parseInt(t1.getText());
                            double avggrade = Double.parseDouble(t2.getText());
                            String lastname = t3.getText().toLowerCase();
                        } catch (Exception error) {
                            String out = "Something Went Wrong, Please Try Again.\n\nPlease make sure that\n1)You have not left Last Name, Year, or Program fields Blank\n2)You have entered a Number for Average grade and not a string.\n3)You have entered an Integer Number for Year and not a string.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });
                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cb.setText("");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        display.setText("");
                    }
                });
                // Adding the Panels
                enterinfostud.add(actionpanel);
                enterinfostud.add(msgpanel);
                enterinfostud.setVisible(true);

                // Disposing the Panels
                home.dispose();
                enterinfogradstud.dispose();
                printinfo.dispose();
                printavg.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        // Creating the Intro Label
        JLabel intro = new JLabel("Student Class Management System");
        intro.setFont(new Font("Arial", Font.BOLD, 30));
        intro.setForeground(new Color(0xc700ff));
        Border emptyBorder = new EmptyBorder(120, 10, 10, 10);
        intro.setBorder(emptyBorder);

        // Creating the intro description
        JTextArea description = new JTextArea(5, 36);
        description.setText(
                "Choose a command by clicking an Option from the Commands menu above to Enter info about a new Student, Enter info about a new GraduateStudent, Print out all student info, Print average of student averages, as well as total number of students, Read input file, File Data output, Lookup via a HashMap key or quit the program.");
        description.setFont(new Font("Verdana", Font.PLAIN, 16));
        description.setForeground(new Color(0xffffff));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setOpaque(false);
        description.setEditable(false);
        Border emptyBorder2 = new EmptyBorder(30, 10, 10, 10);
        description.setBorder(emptyBorder2);

        // Adding the Elements to the Home Frame
        home.add(intro);
        home.add(description);
        home.setVisible(true);
    }
}