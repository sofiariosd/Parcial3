package figura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class graficas extends JFrame{
	
	
	JFrame s;
	private JTextField rField, gField, bField; 
    private JButton botonCirculo, botonCuadrado; 
    private JPanel drawPanel;
    private String currentShape = "";
    private Color currentColor = Color.BLACK;
	
	public graficas() { 
		
		// Aqui es donde se inicia los pasos para crear en JFrame
		s = new JFrame("Sofia Rios Duque");
	
		s.setSize(500,500);
		
		// es importante tener un exit_on_close para que no se llene el espacio del disco 
		//ya que sin esto al cerrarlo se quita de la vista pero como tal sigue abierto
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		s.setLayout(new BorderLayout());
		
		// Aqui ya se edita a partir del Jpanel con los colores para RGB
		
		JPanel controlPanel = new JPanel(new GridLayout(5, 3));
        controlPanel.add(new JLabel("R"));
        rField = new JTextField("100");
        rField.setPreferredSize(new java.awt.Dimension(30, 20));
        controlPanel.add(rField);

        controlPanel.add(new JLabel("G"));
        gField = new JTextField("100");
        gField.setPreferredSize(new java.awt.Dimension(30, 20));
        controlPanel.add(gField);

        controlPanel.add(new JLabel("B"));
        bField = new JTextField("100");
        bField.setPreferredSize(new java.awt.Dimension(30, 20));
        controlPanel.add(bField);
        
        // se crean los bottones de las imagenes del parcial para generar circulos y cuadrados
		botonCirculo = new JButton("Círculo");
		botonCuadrado = new JButton("Cuadrado");
		
		
		controlPanel.add(botonCirculo);
        controlPanel.add(botonCuadrado);
        
        s.add(controlPanel, BorderLayout.WEST);

        
        drawPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibuja la figura seleccionada..
                if (!currentShape.isEmpty()) {
                    g.setColor(currentColor); // Usar el color seleccionado
                    if (currentShape.equals("circle")) {
                        g.fillOval(120, 120, 150, 150); // Dibuja un círculo
                    } else if (currentShape.equals("square")) {
                        g.fillRect(120, 120, 150, 150); // Dibuja un cuadrado
                    }
                }
            }

			private void drawFiguraActual(Graphics g) {
				// TODO Auto-generated method stub
				try {
			        int red = Integer.parseInt(rField.getText());
			        int green = Integer.parseInt(gField.getText());
			        int blue = Integer.parseInt(bField.getText());

			        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
			            throw new IllegalArgumentException("Los valores RGB deben estar dentro del intervalo [0-255]");
			        }

			        Color color = new Color(red, green, blue);
			        g.setColor(color);

			        if (currentShape.equals("circle")) {
			            g.fillOval(120, 120, 150, 150); 
			        } else if (currentShape.equals("square")) {
			            g.fillRect(120, 120, 150, 150); 
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(s, "Por favor, para que funcione ingrese valores numéricos válidos para RGB, deben de estar en el intervalo [0-255].");
			    } catch (IllegalArgumentException ex) {
			        JOptionPane.showMessageDialog(s, ex.getMessage());
			    }
				
			}
        };
        
        drawPanel.setBackground(Color.WHITE);
        s.add(drawPanel, BorderLayout.CENTER);

        
        botonCirculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setShapeAndColor("circle");
                drawPanel.repaint();
            }
        });


        botonCuadrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setShapeAndColor("square");
                drawPanel.repaint();
            }
        });


        s.setVisible(true);
    }
	
	
	// dibujar como tal las imagenes con valores para RGB (Red, Green, Blue)
	 private void setShapeAndColor(String shape) {
	        try {
	            int red = Integer.parseInt(rField.getText());
	            int green = Integer.parseInt(gField.getText());
	            int blue = Integer.parseInt(bField.getText());

	            // Validar que los valores estén en el rango [0-255]
	            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
	                throw new IllegalArgumentException("Los valores RGB deben estar entre 0 y 255.");
	            }

	            // Actualizar la figura y el color seleccionados
	            currentShape = shape;
	            currentColor = new Color(red, green, blue);

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(s, "Por favor, ingrese valores numéricos válidos para RGB.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(s, ex.getMessage());
	        }
	    }
    
	 public static void main(String[] args) {
	        new graficas();
	    }
    }

		
	
