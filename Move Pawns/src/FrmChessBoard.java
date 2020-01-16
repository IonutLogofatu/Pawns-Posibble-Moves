import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class frmChessBoard extends JFrame implements MouseListener
{
    public static void main(String[] args)
    {
        final frmChessBoard app = new frmChessBoard();
    }

    public frmChessBoard(){
        c = getContentPane();
        setBounds(100, 100, 470, 495);
        setBackground(new Color(204, 204, 204));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c.setLayout(null);
        pnlMain.setBounds(3, 3, 460, 460);
        pnlMain.setBackground(new Color(255, 255, 255));
        c.add(pnlMain);
        this.drawChessBoard();
        this.arrangeChessPieces();
        show();
    }


    public void mouseClicked(MouseEvent e)
    {
        if(bMyTurn)
        {
            System.out.println("White");
                Object source = e.getSource();
                JPanel pnlTemp = (JPanel) source;
                int intX = (pnlTemp.getX() / 57);
                int intY = (pnlTemp.getY() / 57);
                this.boolMoveSelection = !this.boolMoveSelection;
            if(clickWhite==true || strChessBoard[intY][intX].equals("PW")) {
                clickWhite = true;
                if (this.boolMoveSelection) {
                    this.pntMoveFrom = new Point(intX, intY);
                    if (this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals(""))
                        this.boolMoveSelection = !this.boolMoveSelection;
                    if (this.boolMoveSelection) {
                        this.makeChessPieceDifferent(true);
                        if(strChessBoard[this.pntMoveFrom.y-1][this.pntMoveFrom.x].equals("  ")){
                            pnlChessCells[this.pntMoveFrom.y-1][this.pntMoveFrom.x].setBackground(Color.RED);
                        }
                        if(strChessBoard[this.pntMoveFrom.y-1][this.pntMoveFrom.x-1].equals("PB")){
                            pnlChessCells[this.pntMoveFrom.y-1][this.pntMoveFrom.x-1].setBackground(Color.RED);
                        }
                        if(strChessBoard[this.pntMoveFrom.y-1][this.pntMoveFrom.x +1].equals("PB")){
                            pnlChessCells[this.pntMoveFrom.y-1][this.pntMoveFrom.x +1].setBackground(Color.RED);
                        }
                    }
                } else {
                    this.pntMoveTo = new Point(intX, intY);
                    if (!this.pntMoveFrom.equals(this.pntMoveTo)) {
                        if (this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim() != "")
                            if (isMoveValidWhite(this.pntMoveTo.y, this.pntMoveTo.x,this.pntMoveFrom.x,this.pntMoveFrom.y)) {

                                this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x] = this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString();

                                this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x] = "  ";
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x+1);
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x);
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x-1);
                                changeColor((this.pntMoveFrom.y),this.pntMoveFrom.x);
                                this.moveChessPiece();
                                bMyTurn = false;
                                clickWhite = false;
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid Move Request.", "Warning", JOptionPane.ERROR_MESSAGE);

                                this.makeChessPieceDifferent(false);
                                clickWhite = false;
                                if((this.pntMoveFrom.y-1)%2==1 && this.pntMoveFrom.x%2==0){
                                    makeWhite(this.pntMoveFrom.y-1,this.pntMoveFrom.x);
                                }
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x +1);
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x);
                                changeColor((this.pntMoveFrom.y-1),this.pntMoveFrom.x-1);
                                changeColor((this.pntMoveFrom.y),this.pntMoveFrom.x);
                            }
                    } else {
                        this.makeChessPieceDifferent(false);
                    }
                }
            }
        }else {
            Object source = e.getSource();

            JPanel pnlTemp = (JPanel) source;

            int intX = (pnlTemp.getX() / 57);
            int intY = (pnlTemp.getY() / 57);
            this.boolMoveSelection = !this.boolMoveSelection;
            if (strChessBoard[intY][intX].equals("PB") || clickBlack == true) {
                clickBlack = true;
                if (this.boolMoveSelection) {
                    this.pntMoveFrom = new Point(intX, intY);
                    if (this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) {
                        this.boolMoveSelection = !this.boolMoveSelection;
                    }
                    if (this.boolMoveSelection) {
                        if (strChessBoard[this.pntMoveFrom.y + 1][this.pntMoveFrom.x].equals("  ")) {
                            pnlChessCells[this.pntMoveFrom.y + 1][this.pntMoveFrom.x].setBackground(Color.RED);
                        }
                        if (strChessBoard[this.pntMoveFrom.y + 1][this.pntMoveFrom.x - 1].equals("PW")) {
                            pnlChessCells[this.pntMoveFrom.y + 1][this.pntMoveFrom.x - 1].setBackground(Color.RED);
                        }
                        if (strChessBoard[this.pntMoveFrom.y + 1][this.pntMoveFrom.x + 1].equals("PW")) {
                            pnlChessCells[this.pntMoveFrom.y + 1][this.pntMoveFrom.x + 1].setBackground(Color.RED);
                        }
                        this.makeChessPieceDifferent(true);
                    }
                } else {
                    this.pntMoveTo = new Point(intX, intY);
                    if (!this.pntMoveFrom.equals(this.pntMoveTo)) {
                        if (this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim() != "")
                            if (isMoveValidBlack(this.pntMoveTo.y, this.pntMoveTo.x,this.pntMoveFrom.x,this.pntMoveFrom.y)) {
                                this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x] = this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString();
                                this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x] = "  ";

                                this.moveChessPiece();

                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x +1);
                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x);
                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x-1);
                                changeColor((this.pntMoveFrom.y),this.pntMoveFrom.x);
                                bMyTurn = true;
                                clickBlack = false;
                            } else {

                                JOptionPane.showMessageDialog(this, "Invalid Move Request.", "Warning", JOptionPane.ERROR_MESSAGE);

                                this.makeChessPieceDifferent(false);
                                clickBlack = false;
                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x +1);
                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x);
                                changeColor((this.pntMoveFrom.y+1),this.pntMoveFrom.x-1);
                                changeColor((this.pntMoveFrom.y),this.pntMoveFrom.x);
                            }

                    } else {
                        this.makeChessPieceDifferent(false);
                    }
                }
            }
        }

    }

    private void changeColor(int y, int x){
        if((y)%2==1 && (x)%2==0){
            makeBlack(y,x);
        }
        if((y)%2==1 && (x)%2==1){
            makeWhite(y,x);
        }
        if(y%2==0 && (x)%2==0){
            makeWhite(y,x);
        }
        if(y%2==0 && (x)%2==1){
            makeBlack(y,x);
        }
    }

    // This method checks if attempted move is valid or not

    private boolean isMoveValidWhite(int y, int x, int xInit, int yInit)
    {
        System.out.println(y + " " + x + " !!! " + yInit + "  " + xInit);
        if((strChessBoard[y][x].equals("  ") || (strChessBoard[y][x].equals("PB"))) && ((x == xInit-1 || x == xInit+1 || x == xInit) && y == (yInit -1))) {
            return true;
        }else {
            return false;
        }
    }
    private boolean isMoveValidBlack(int y, int x, int xInit, int yInit)
    {
        if((strChessBoard[y][x].equals("  ") || strChessBoard[y][x].equals("PW")) && ((x == xInit-1 || x == xInit+1 || x == xInit) && y == yInit +1)) {
            return true;
        }
        return false;
    }

    private void makeWhite(int y, int x){
        pnlChessCells[y][x].setBackground(Color.WHITE);
    }

    private void makeBlack(int y, int x){
        pnlChessCells[y][x].setBackground(Color.DARK_GRAY);
    }

    // This method makes the selected chess piece looks like selected

    private void makeChessPieceDifferent(boolean bSelected) {
        for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++) {
            if (this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1) {
                JLabel lblTemp = (JLabel) this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z);
                lblTemp.setEnabled(!bSelected);
            }
        }
    }

    // If class level variables Point-From and Point-To are set,

    // then this method actually moves a piece, if any exists, from

    // one cell to the other

    private void moveChessPiece() {
        for (int z = 0; z < this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponentCount(); z++){
            if (this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1) {
                this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].remove(z);
                this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].repaint();
            }
        }
        for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++) {
            if (this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1) {
                this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].remove(z);
                this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].repaint();
            }
        }
        this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].add(this.getPieceObject(this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x]), BorderLayout.CENTER);
        this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].validate();
    }

    // Given the code of a piece as a string, this method instantiates

    // a label object with the right image inside it

    private JLabel getPieceObject(String strPieceName) {
        JLabel lblTemp;
      if(strPieceName.equals("PB")) {
          lblTemp = new JLabel(this.pawnBlack);
      }else if(strPieceName.equals("PW")) {
          lblTemp = new JLabel(this.pawnWhite);
      }else {
          lblTemp = new JLabel();
      }
        return lblTemp;
    }
    private void arrangeChessPieces()
    {
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                this.pnlChessCells[y][x].add(this.getPieceObject(strChessBoard[y][x]), BorderLayout.CENTER);
                this.pnlChessCells[y][x].validate();
            }
        }
    }

    private void drawChessBoard()
    {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                pnlChessCells[y][x] = new JPanel(new BorderLayout());
                pnlChessCells[y][x].addMouseListener(this);
                pnlMain.add(pnlChessCells[y][x]);
                if (y % 2 == 0) {
                    if (x % 2 != 0) {
                        pnlChessCells[y][x].setBackground(Color.DARK_GRAY);
                    } else {
                        pnlChessCells[y][x].setBackground(Color.WHITE);
                    }
                } else if (x % 2 == 0) {
                    pnlChessCells[y][x].setBackground(Color.DARK_GRAY);
                } else {
                    pnlChessCells[y][x].setBackground(Color.WHITE);
                }
            }
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    private JPanel[][] pnlChessCells = new JPanel[8][8];
    private JPanel pnlMain = new JPanel(new GridLayout(8,8));
    private String[][] strChessBoard = new String[][] {{"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"} ,{"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}};
    private ImageIcon pawnBlack = new ImageIcon("C:/Users/ION/Desktop/joc/Images/black.png");
    private ImageIcon pawnWhite = new ImageIcon("C:/Users/ION/Desktop/joc/Images/white.png");
    private boolean boolMoveSelection = false, bWhite = true, bMyTurn = true;
    private Point pntMoveFrom, pntMoveTo;
    private Container c;
    public static boolean clickWhite = false, clickBlack = false;

}