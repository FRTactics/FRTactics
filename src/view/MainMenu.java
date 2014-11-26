package view;

import controllers.screenControllers.MainMenuController;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.ImageContainer;

public class MainMenu extends JPanel 
{
    // buttons
    private final GameButton newGameButton;
    private final GameButton optionsButton;
    private final GameButton quitButton;
    // containers
    private final JPanel buttonPanel;
    private final Image background;
    public MainMenu()
    {
        // instantiate all of the buttons with the appropriate images
        newGameButton = new GameButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.NEW_GAME_SWORD));
        optionsButton = new GameButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.OPTIONS_SWORD));
        quitButton = new GameButton(ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.QUIT_SWORD));
        buttonPanel = new JPanel();        // create button panel, which holds all of the buttons
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));    // used box layout because it's awesome
        buttonPanel.setOpaque(false);
        // add the rest of the buttons to the button container
        buttonPanel.add(Box.createVerticalGlue());      // create buffer to make button placement look better
        buttonPanel.add(newGameButton);                 
        buttonPanel.add(Box.createVerticalGlue());      // create buffer to make button placement look better
        buttonPanel.add(optionsButton);                 
        buttonPanel.add(Box.createVerticalGlue());      // create buffer to make button placement look better
        buttonPanel.add(quitButton);                 
        buttonPanel.add(Box.createVerticalGlue());      // create buffer to make button placement look better
        background = ImageContainer.getInstance().retrieveMenuImage(ImageContainer.MenuImage.MAIN_MENU_BACKGROUND);    // set the background of the screen
        this.setLayout(new BorderLayout());         // used border layout for the entire screen
        this.add(buttonPanel, BorderLayout.EAST);   // button panel is placed on the right side of the screen
        
    }
    @Override
    public void paintComponent(Graphics g)              // paints the background onto the screen
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    // methods that associate the buttons with the controllers
    public void addNewGameButtonController(MouseAdapter adapter)    // method used to add listener to the new game button
    {
        newGameButton.addMouseListener(adapter);
    }
    public void addOptionsButtonController(MouseAdapter adapter)    // method used to add listener to the options button
    {
        optionsButton.addMouseListener(adapter);
    }
    public void addQuitButtonController(MouseAdapter adapter)       // method used to add listener to the quit button
    {
        quitButton.addMouseListener(adapter);
    }
    
    // getter methods
    public GameButton getNewGameButton()                            // getter method for the new game button 
    {
        return newGameButton;
    }
    public GameButton getOptionsButton()                            // getter method for the options button
    {
        return optionsButton;
    }
    public GameButton getQuitButton()                               // getter method for the quit button
    {
        return quitButton;
    }
}

