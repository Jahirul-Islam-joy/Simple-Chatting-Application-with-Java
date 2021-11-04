package chatting.application;

import javax.swing.*;   //for design the frame or layout
import javax.swing.border.*; // for frame border
import java.awt.*;          //abstract tool kit like image 
import java.awt.event.*;   //for an event action listener
import java.net.*;          // for socket programming or networking 
import java.io.*;           // for input output 

import java.util.Calendar;   // time, month and week get 
import java.text.SimpleDateFormat;   // time and date format 


// 
public class server2 implements ActionListener{
    // Everythings declare in globally
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();  //Creates a Box that displays its components from top to bottom
    
    static ServerSocket skt;    //use for server 
    static Socket s;           //for soket 
    static DataInputStream din;   
    static DataOutputStream dout;
    
    Boolean typing;      // 
    
    server2(){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setLayout(null);      //null for own layout 
        p1.setBackground(new Color(7, 94, 84));  // RGB format
        p1.setBounds(0, 0, 450, 70);   // For our custom layout four valoue firs two for location 2nd two for materials hight width
        f1.add(p1);
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png")); //creating a object for load an icon from the disk
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);  // Resizing the image cahnge image lenth or depth  its a class of awt 
       ImageIcon i3 = new ImageIcon(i2);    //again convert into imageicon
       JLabel l1 = new JLabel(i3);         // set in a label
       l1.setBounds(5, 17, 30, 30); //set layout for arrow icon 
       p1.add(l1);   //add method is use for to add anything in the frame "Here we add the arrow icon on the pannel"
       
       
       //Mouse Event
       
       l1.addMouseListener(new MouseAdapter(){     //when we click on arrow icon then it will be exit
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/s.png")); // server icon
       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
       JLabel l2 = new JLabel(i6);
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
       
       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));   //video icon
       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);  
       ImageIcon i9 = new ImageIcon(i8);
       JLabel l5 = new JLabel(i9);
       l5.setBounds(290, 20, 30, 30);
       p1.add(l5);
       
       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));  //audio icon
       Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i13 = new ImageIcon(i12);
       JLabel l6 = new JLabel(i13);
       l6.setBounds(350, 20, 35, 30);
       p1.add(l6);
       
       ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));    //3 dont icon
       Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
       ImageIcon i16 = new ImageIcon(i15);
       JLabel l7 = new JLabel(i16);
       l7.setBounds(410, 20, 13, 25);
       p1.add(l7);
       
       
       JLabel l3 = new JLabel("Sojib Ali");  //server name 
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE); //font color
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);   
       
       
       JLabel l4 = new JLabel("Active Now");  //status
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 100, 20);
       p1.add(l4);  
       
       ///visiable active status when user active 
       Timer t = new Timer(1, new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
           }
       });
       
       t.setInitialDelay(2000);
       
       
       
       //a1 is for when we click send button then the massage show in a1 pannel
       a1 = new JPanel();
       //a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       //f1.add(a1);
       
       JScrollPane sp = new JScrollPane(a1);
       sp.setBounds(5, 75, 440, 570);
       sp.setBorder(BorderFactory.createEmptyBorder());
       f1.add(sp);
       
       
       
       // it is for text field 
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       f1.add(t1);
       
       
       //when press any key then it will show that user are typeing 
       t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){
               l4.setText("typing...");
               
               t.stop();
               
               typing = true;
           }
           
           public void keyReleased(KeyEvent ke){
               typing = false;
               
               if(!t.isRunning()){
                   t.start();
               }
           }
       });
       
       
       //send button design
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       f1.add(b1);
       
       
       
       //main frame design
       f1.getContentPane().setBackground(Color.WHITE);  //background color
       f1.setLayout(null);  //there is many layout so we set null
       f1.setSize(470, 750);         //frame size 
       f1.setLocation(400, 200);     //locatiion where the frame will visible 
       f1.setVisible(true);           //if true then will  visible the frame false not visible
         
    }
    
    
    //Override the action event ae 
    public void actionPerformed(ActionEvent ae){
        try{
            String out = t1.getText();  //for get the text 
            
            JPanel p2 = formatLabel(out);
            
            a1.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            
            a1.add(vertical, BorderLayout.PAGE_START);
            
            //a1.add(p2);
            dout.writeUTF(out);  //send the write text out 
            t1.setText("");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);  //method of a AtomicReference class is used to set the value of this AtomicReference object with memory effects as specified by VarHandle.
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
    
    
    
    //Main method 
    public static void main(String[] args){
        new Server().f1.setVisible(true);
        
        String msginput = "";
        try{
            skt = new ServerSocket(6001);  // set the port no 
            while(true){
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());      //declare input output stream 
                dout = new DataOutputStream(s.getOutputStream());
            
	        while(true){
	                msginput = din.readUTF();           //when you send anything the client will read the massage in the left side 
                        JPanel p2 = formatLabel(msginput);
                        
                        JPanel left = new JPanel(new BorderLayout()); // 
                        left.add(p2, BorderLayout.LINE_START);
                        vertical.add(left);
                        f1.validate();
            	}
                
            }
            
        }catch(Exception e){}
    }    
}
