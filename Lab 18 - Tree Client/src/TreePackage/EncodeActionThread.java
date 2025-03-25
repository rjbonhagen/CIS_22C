package TreePackage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;


import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import TreePackage.*;
 
/**
 * A Thread that contains the application we are going to animate
 * 
 * @author Charles Hoot 
 * @version 4.0
 */

   
    
public class EncodeActionThread extends ActionThread
{
    int[] counts;

        
    /**
     * Constructor for objects of class EncodeActionThread
     */
    public EncodeActionThread()
    {
        super(); 
    }
    
    public String getApplicationTitle()
    {
        
         return "Encode a file using Huffman Coding (Skeleton)";
    }


    

    // **************************************************************************
    // This is application specific code
    // **************************************************************************    

    // These are the variables that are parameters of the application and can be
    // set via the application specific GUI
    // Make sure they are initialized
    private String textFileName = "message1.txt";

    
    // Displayed items
    private Message myMessage;
    private Code myCode;
    private HuffmanTree<Character> [] myTrees;
    private int numberOfTrees;
   
    // ADD YOUR PRIVATE VARIABLE HERE

    
    private boolean textFileNotFound = false;
    private boolean treeCreated = false;
    
    public void init() 
    {
        myMessage = new Message();
        myCode = new Code();
        myTrees = null;
        numberOfTrees = 0;
        treeCreated = false;
        textFileNotFound = false;
    }
        

    public void executeApplication()
    {
    	
    	
        loadMessage(textFileName, myMessage);
        
        // ADD CODE HERE FOR A SINGLE RUN OF THE APPLICATION
        int[] counts = getCounts(myMessage);
        for (int i = 0; i < counts.length; i++) {
        	if (counts[i] != 0) 
        		System.out.print(intToChar(i) + " | " + counts[i] + ", ");
        }

        // MAKE TREES
        myTrees = getInitialTrees(counts);
        numberOfTrees = myTrees.length;
        animationPause();
        
        int count = 0;
    	for (int i = 0; i < myTrees.length; i++) {
        	if (myTrees[i] != null)
        		count++;
        }
        System.out.println(count);
        while (count > 1) {
            combineTrees();
            count--;
        }
        for (int i = 0; i < myTrees.length; i++) {
        	if (myTrees[i] != null) {
        		myTrees[0] = myTrees[i];
        		myTrees[i] = null;
        	}
        }
        treeCreated = true;
       
        animationPause();
        
        myMessage.reset();
        while (myMessage.hasNext()) {     
        	Character myChar = myMessage.next();
        	System.out.println(myChar);
        	encodeCharacter(myChar, myCode, myTrees[0]);
            System.out.println(myCode.toString());  
        	myTrees[0].resetCurrentNode();
        }
        
        
    }
    
    
    
    /**
     * Count the number of times each character appears in the message. 
     *
     * @param aMessage An object that holds all the characters of the message
     * to be encoded.
     * @return An array with the count for the number of times each character occurs.
     */
    public int[] getCounts(Message aMessage) {
        int[] result = new int[128]; // all possible characters
        
        while (aMessage.hasNext()) {
            char c = aMessage.next();
            if (charToInt(c) < 128) {
                result[c]++;
            }
        }
        
        return result;
    }

    

        
    @SuppressWarnings("unchecked") // We will create an array of Huffman Trees
    // and fill it with an appropriate type.
    
    /**
     * Create the initial forrest of trees. 
     *
     * @param counts The frequency of each character
     * to be encoded.
     * @return The forest of trees for each single letter.
     */
    public HuffmanTree<Character>[] getInitialTrees(int[] count) {
        int size = 0;
        for (int freq : count) {
            if (freq != 0) 
            	size++;
        }

        HuffmanTree<Character>[] result = (HuffmanTree<Character>[]) new HuffmanTree[size];

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                List<Character> c = new ArrayList<>();
                c.add(intToChar(i));

                SymbolFrequencyPacket<Character> packet = new SymbolFrequencyPacket<>(c, count[i]);
                result[index++] = new HuffmanTree<>(packet);
            }
        }

        return result;
    }
    


    /**
     * Reduce the number of trees in the forest by one. This will operate by side effect
     * on the forest of trees contained in the variable myTrees.
    */
    public void combineTrees()
    {
    	@SuppressWarnings("unchecked")
		HuffmanTree<Character> smallest = new HuffmanTree<>(new SymbolFrequencyPacket<>(new ArrayList<>(), Integer.MAX_VALUE));
    	int smallestIndex = -1;
    	
    	for (int i = 0; i < myTrees.length; i++) {
    		if (myTrees[i] != null) {
    			if (myTrees[i].getRootData().getFrequency() < smallest.getRootData().getFrequency()) {
        			smallest = myTrees[i];	
        			smallestIndex = i;
        		}
    		}	
    	}
    	
    	HuffmanTree<Character> secondSmallest = new HuffmanTree<>(new SymbolFrequencyPacket<>(new ArrayList<>(), Integer.MAX_VALUE));
    	int secondSmallestIndex = -1;
    	
    	for (int i = 0; i < myTrees.length; i++) {
    		if (myTrees[i] != null) {
	    		if (myTrees[i].getRootData().getFrequency() < secondSmallest.getRootData().getFrequency() 
	    				&& myTrees[i].getRootData().getFrequency() > smallest.getRootData().getFrequency()) {
	    			secondSmallest = myTrees[i];	
	    			secondSmallestIndex = i;
	    		}
    		}
    	}
    	
    	List<Character> concat= new ArrayList<Character>();
    	concat.addAll(smallest.getRootData().symbolList);
    	concat.addAll(secondSmallest.getRootData().symbolList);
 
    	SymbolFrequencyPacket<Character> combined = new SymbolFrequencyPacket<Character>(concat, smallest.getRootData().getFrequency() + secondSmallest.getRootData().getFrequency());
    	
    	HuffmanTree<Character> combine = new HuffmanTree<>(combined, smallest, secondSmallest);
    	
    	myTrees[smallestIndex] = combine;
    	myTrees[secondSmallestIndex] = null;

    	animationPause();
    }    
    
    /**
     * Encode a single symbol using a Huffman tree. 
     *
     * @param c The symbol to be encoded.
     * @param codeBuffer The code buffer where the code characters will be placed.
     * @param coder A Huffman tree used to encode the character.
     */

    public void encodeCharacter(Character c, Code codeBuffer, HuffmanTree<Character> coder)
    {
    	boolean found = false;
    	while (!found) {
	    	if (!coder.isSingleSymbol()) {
	    		
	    		if (coder.checkLeft(c)) {
	    			coder.advanceLeft();
	    			codeBuffer.addCharacter('0');
	    		}
	    		else if (coder.checkRight(c)) {
	    			coder.advanceRight();
	    			codeBuffer.addCharacter('1');
	    		}
	    		else break;
	    	}
    		else found = true;
    	}
       
    }        
    
    
    
    
   /**
     * Convert an integer value into an ascii character.
     *
     * @param x The integer value.
     *
     * @return The character or null if unable to convert.
     */
    public Character intToChar(int x)
    {
        Character result = null;
        
        if (x <= 127 && x >= 0)
        {
            try
            {
                byte [] b = new byte[1];
                b[0] = (byte) x;
                String st = new String(b,"US-ASCII");
                result =  new Character(st.charAt(0));
                
            }
            catch (UnsupportedEncodingException e)
            {
                System.out.println("US-ASCII encoding is not supported - Whoops");
            }
        }
        return result;
    }
    
    /**
     * Convert a character into an ascii integer.
     *
     * @param c The character value to convert.
     *
     * @return The integer or null if unable to convert.
     */
   
    public Integer charToInt(char c)
    {
        Integer result = null;
        char[] in = new char[1];
        in[0] = c;
        String toConvert = new String(in);
        byte[] out;
        try
        {
            out = toConvert.getBytes("US-ASCII");
            if(out.length == 1)
                result = new Integer( (int) out[0]);
         } 
        catch (UnsupportedEncodingException e)
        {
            System.out.println("US-ASCII encoding is not supported - Whoops");
        }
        
        return result;
    }
    
    /**
     * Load the words into the Message.
     *
     * @param theFileName The name of the file holding the message.
     *
     * @param theMessage The object to load.
     */
    public void loadMessage(String theFileName, Message theMessage)
    {
        Scanner input;        
        try
        {
            String inString;
            
            input = new Scanner( new File(theFileName ) );
          
            while(input.hasNextLine())  // read until  end of file
            {
                theMessage.addLine(input.nextLine());
            }          
            System.out.println(theMessage);
        }
        catch(Exception e)
        {
            System.out.println("There was an error in reading or opening the file: " +theFileName);
            System.out.println(e.getMessage());
            textFileNotFound = true;
            forceLastPause();
            throw new ResetApplicationException("Could not read message File");
        }
 
    }
    


    private static int DISPLAY_HEIGHT = 500;
    private static int DISPLAY_WIDTH = 700;
    
    public JPanel createAnimationPanel()
    {
        return new AnimationPanel();
    }

    private static int MESSAGE_HEIGHT = 60;
    private static int CODE_HEIGHT = 60;
    private static int CONTROLS_HEIGHT = 50;
    private static int LINE_HEIGHT = 15;

    // This privately defined class does the drawing the application needs
    public class AnimationPanel extends JPanel
    {
        public AnimationPanel()
        {
            super();
            setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        }
        
        public void paintComponent(Graphics g)
        {
            String toDraw;
            super.paintComponent(g);
            toDraw = "Tree created: " + treeCreated;
            g.drawString(toDraw, 20, DISPLAY_HEIGHT - CONTROLS_HEIGHT - LINE_HEIGHT);
                        
            if(textFileNotFound)
            {
                toDraw = "Text file not found; aborting";
                g.setColor(Color.red);
                g.drawString(toDraw, 20, DISPLAY_HEIGHT - CONTROLS_HEIGHT);
                g.setColor(Color.black);
            }             
            
            // Now draw the Huffman trees if they are available
            if(myTrees != null)
            {
                int base = 0;
                int delta = DISPLAY_WIDTH/numberOfTrees;
                for(int i=0; i<numberOfTrees; i++)
                {            
                    if(myTrees[i] != null)
                        myTrees[i].drawOn(g, base, base+delta, 20 + MESSAGE_HEIGHT + CODE_HEIGHT);
                    base += delta;
                }
            }
                
            // Now draw the message if it is available
            if(myMessage != null)
                myMessage.drawOn(g, 0, 20);
                
            // Now draw the code if it is available
            if(myCode != null)
                myCode.drawOn(g, 0, 20 + MESSAGE_HEIGHT);
        }
    }
    
    // **************************************************************************
    // This is the application specific GUI code
    // **************************************************************************    

    private JTextField textNameTextField;
    private JLabel setupStatusLabel;
    private JPanel setupPanel;
    
    public void setUpApplicationSpecificControls()
    {
        getAnimationPanel().setLayout(new BorderLayout());
        
        
        textNameTextField = new JTextField("message1.txt");
        textNameTextField.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent event) 
                {
                    textNameTextFieldHandler();
                    getAnimationPanel().repaint();
                }
            }
        );

 
        
        setupStatusLabel = new JLabel("");
        
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(2,2));
        
        setupPanel.add(new JLabel("Text file name:"));
        setupPanel.add(textNameTextField);
        setupPanel.add(setupStatusLabel);
        
        getAnimationPanel().add(setupPanel,BorderLayout.SOUTH);
               
    }

   
   
    private void textNameTextFieldHandler()
    {
    try
        {
            if(applicationControlsAreActive())   // Only change if we are in the setup phase
            {
                String input = textNameTextField.getText().trim();
                File test = new File(input);
                if(test.canRead())
                {
                    textFileName = input;
                    setupStatusLabel.setText("text file is now " + input);
                }
                else
                {
                    setupStatusLabel.setText("Could not read " + input);
                    textNameTextField.setText("");
                }
                
            }
        }
        catch(Exception e)
        {
            // don't change the name if we had an exception
            setupStatusLabel.setText("bad text file name");

        }
    
    }

            
} // end class ActionThread

