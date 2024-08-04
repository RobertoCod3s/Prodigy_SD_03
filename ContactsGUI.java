import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsGUI {
    private JFrame frame;
    private JFrame addContactFrame;
    private JFrame editContactFrame;
    private JFrame searchFrame;
    public JButton addButton;
    public JButton editButton;

    private JPanel contactsPanel;
    private ContactList allContacts;
    private Contact contactToEdit;

    public ContactsGUI() {
        allContacts = new ContactList();
        frame = new JFrame("MyContacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);





        // Contacts Panel with BoxLayout (Vertical)

        contactsPanel = new JPanel();
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contactsPanel);
        // Check if Array Empty
            //Contact PanelCubes
        JLabel noSavesLabel = new JLabel("No Contacts Saved");
        noSavesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contactsPanel.add(noSavesLabel);




        //Add Contact Frame
        addContactFrame = new JFrame("Add Contact");
        addContactFrame.setLayout(new BorderLayout());
        addContactFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addContactFrame.setSize(300, 220);
        addContactFrame.setLocation(590, 0);

        JPanel addContactPanel = new JPanel();
        addContactPanel.setLayout(new BoxLayout(addContactPanel, BoxLayout.Y_AXIS));

        // Create text fields
        JLabel nameFieldLabel1 = new JLabel("Name:");
        JTextField nameField1 = new JTextField(20);
        JLabel surnameFieldLabel2 = new JLabel("Surname:");
        JTextField surnameField2 = new JTextField(20);
        JLabel phoneNumLabel3 = new JLabel("Phone Number:");
        JTextField phoneNumRField3 = new JTextField(20);
        JLabel emailLabel4 = new JLabel("Email:");
        JTextField emailField4 = new JTextField(20);


        // Create button
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField1.getText();
                String surname = surnameField2.getText();
                String phoneNumber = phoneNumRField3.getText();
                String email = emailField4.getText();

                Contact newContact = new Contact(name, surname, phoneNumber, email);
                if(allContacts.exists(newContact)) {
                    JOptionPane.showMessageDialog(null, "Contact already exists!");
                    nameField1.setText("");
                    surnameField2.setText("");
                    phoneNumRField3.setText("");
                    emailField4.setText("");
                } else {
                    allContacts.addContact(newContact);

                    // Optionally, you can clear the text fields after adding the contact
                    nameField1.setText("");
                    surnameField2.setText("");
                    phoneNumRField3.setText("");
                    emailField4.setText("");
                    contactsPanel.removeAll();
                    contactsPanel.revalidate();
                    contactsPanel.repaint();
                    for (Contact contact : allContacts.contacts) {
                        contactsPanel.add(contactCube(contact));
                    }


                    // Notify user that contact was added
                    JOptionPane.showMessageDialog(null, "Contact added successfully!");

                }
            }
        });


        // Add components to panel
        addContactPanel.add(nameFieldLabel1);addContactPanel.add(nameField1);addContactPanel.add(surnameFieldLabel2);addContactPanel.add(surnameField2);
        addContactPanel.add(phoneNumLabel3);addContactPanel.add(phoneNumRField3);addContactPanel.add(emailLabel4);addContactPanel.add(emailField4);
        //Adding panel to north frame
        addContactFrame.add(addContactPanel,BorderLayout.CENTER);
        addContactFrame.add(addButton,BorderLayout.SOUTH);



        // Edit Frame
        editContactFrame = new JFrame("Edit Contact");
        editContactFrame.setLayout(new BorderLayout());
        editContactFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editContactFrame.setSize(300, 220);
        editContactFrame.setLocation(590, 220);

        JPanel editContactPanel = new JPanel();
        editContactPanel.setLayout(new BoxLayout(editContactPanel, BoxLayout.Y_AXIS));

        // Create text fields
        JLabel nameFieldLabel2 = new JLabel("Name:");
        JTextField nameField2 = new JTextField(20);
        JLabel surnameFieldLabel3 = new JLabel("Surname:");
        JTextField surnameField3 = new JTextField(20);
        JLabel phoneNumLabel5 = new JLabel("Phone Number:");
        JTextField phoneNumField5 = new JTextField(20);
        JLabel emailFieldLabel5 = new JLabel("Email:");
        JTextField emailField5 = new JTextField(20);

        // Create button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact toChange = contactToEdit;
                allContacts.removeContact(contactToEdit);
                toChange.name = nameField2.getText();
                toChange.surname = surnameField3.getText();
                toChange.phoneNumber = phoneNumField5.getText();
                toChange.email = emailField5.getText();
                allContacts.addContact(toChange);

                contactsPanel.removeAll();
                contactsPanel.revalidate();
                contactsPanel.repaint();
                for (Contact contact : allContacts.contacts) {
                    contactsPanel.add(contactCube(contact));
                }
                if(allContacts.exists(toChange)) {
                    JOptionPane.showMessageDialog(null, "Contact edited successfully!");

                }
                else if(allContacts.exists(contactToEdit)){
                    JOptionPane.showMessageDialog(null, "Contact to edit does not exist!");

                }else{
                    JOptionPane.showMessageDialog(null, "Error Occured When Updating");

                }


            }
        });





        // Add components to panel
        editContactPanel.add(nameFieldLabel2);
        editContactPanel.add(nameField2);
        editContactPanel.add(surnameFieldLabel3);
        editContactPanel.add(surnameField3);
        editContactPanel.add(phoneNumLabel5);
        editContactPanel.add(phoneNumField5);
        editContactPanel.add(emailFieldLabel5);
        editContactPanel.add(emailField5);

// Adding panel to north frame
        editContactFrame.add(editContactPanel, BorderLayout.CENTER);
        editContactFrame.add(editButton, BorderLayout.SOUTH);
        //editContactFrame.setVisible(true);





        //PANEL FOR SEARCH BUTTON
        searchFrame = new JFrame("Search");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setSize(200, 100);
        searchFrame.setLocation(0, 410);
        searchFrame.setLayout(new BoxLayout(searchFrame, BoxLayout.Y_AXIS));





        // Adding Contact Panel to Frame
        frame.add(configurePanel(),BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    public JPanel configurePanel() {
        JPanel configurePanel = new JPanel(new BorderLayout());

            //Left Panel for search bar and button
            JPanel configureLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //Creating components for left Panel
        // Icon created first
        try {
            BufferedImage originalImage = ImageIO.read(new File("call-icon.png")); // Load the image
            Image resizedImage = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize the image
            ImageIcon icon = new ImageIcon(resizedImage); // Create an ImageIcon with the resized image

            // Create and add the icon label
            JLabel iconLabel = new JLabel(icon);
            configureLeftPanel.add(iconLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
            JLabel myContactsLabel = new JLabel("MyContacts");
            myContactsLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
            myContactsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField configureSearchTField = new JTextField(20);
            JButton configureSearchButton = new JButton("Search");
            configureSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toFind = configureSearchTField.getText();
                ArrayList<Contact> foundContact = allContacts.kindaFindContact(toFind);
                if(foundContact.size()==0) {
                    searchFrame.add(new JLabel("Nothing found"));
                }
                else {
                    for (Contact c :
                            foundContact) {
                        searchFrame.add(contactCube(c));
                    }
                    searchFrame.setVisible(true);
                }

            }});

            //Adding components to left panel
            configureLeftPanel.add(myContactsLabel);configureLeftPanel.add(configureSearchTField);
            configureLeftPanel.add(configureSearchButton);

            //Adding left panel to west of main panel
            configurePanel.add(configureLeftPanel, BorderLayout.WEST);

        //Button for adding contacts
        JButton addContact = new JButton("Add Contact");

        addContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContactFrame.setVisible(true);
            }
        });
        configurePanel.add(addContact,BorderLayout.EAST);



        Border border = new LineBorder(Color.BLACK, 3); // Black border with thickness of 2 pixels
        configurePanel.setBorder(border);
        configurePanel.setPreferredSize(new Dimension(300, 50));

        return configurePanel;
    }
    public JPanel contactCube(Contact c){
        JPanel contactCubePanel = new JPanel(new BorderLayout());

// Left side panel with labels
        // Create the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components horizontally

// Load and resize the icon
        try {
            BufferedImage originalImage = ImageIO.read(new File("contact-icon.png")); // Load the image
            Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize the image
            ImageIcon icon = new ImageIcon(resizedImage); // Create an ImageIcon with the resized image

            // Create and add the icon label
            JLabel iconLabel = new JLabel(icon);
            leftPanel.add(iconLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Create and add the text labels
        leftPanel.add(new JLabel(c.name));
        leftPanel.add(new JLabel(c.surname));
        leftPanel.add(new JLabel(c.phoneNumber));
        leftPanel.add(new JLabel(c.email));

// Right side panel with buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContactFrame.setVisible(true);
                contactToEdit = c;
            }
        });

        JButton deleteButton = new JButton("Delete");
        rightPanel.add(editButton);
        rightPanel.add(deleteButton);


// Adding components to contactCubePanel
        contactCubePanel.setLayout(new BorderLayout());
        contactCubePanel.add(leftPanel, BorderLayout.WEST);
        contactCubePanel.add(rightPanel, BorderLayout.EAST);

        Border border = new LineBorder(Color.BLACK, 1); // Black border with thickness of 2 pixels
        contactCubePanel.setBorder(border);
        contactCubePanel.setPreferredSize(new Dimension(300, 50));

        return contactCubePanel;
    }

    public static void main(String[] args) {
        ContactList contactList = new ContactList();
        for (int i = 1; i <= 20; i++) {
            Contact contact = new Contact(
                    "Name " + i,
                    "Surname " + i,
                    "123-456-789-" + i,
                    "email" + i + "@example.com"
            );
            contactList.addContact(contact);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactsGUI();
            }
        });
    }
}
