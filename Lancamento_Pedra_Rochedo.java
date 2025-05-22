import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.DecimalFormat;

// OBS ainda não foi implenentado a segunda parte do código de verificar se
//a pedra cairá antes do rochedo, batera na parede do rochedo, caira em cima do rochedo
//ou caira depois do rochedo
public class Lancamento_Pedra_Rochedo implements ActionListener {

        // Variáveis globais
        JFrame frame;
        JButton calcBtn, resetBtn, checkBtn;
        JLabel angleText, initSpeedText, rockHeightText, timeText, rockWidthtText, rockDistanceText;
        JTextField initSpeedInput, initSpeedOutput, angleInput, distanceRockInput,
                        timeAfterLaunchInput, rockWidthInput, rockHeightInput;
        // JTextField initSpeedInput, angleInput, distanceRockInput,distanceRockOutput,
        // timeOutput, speedOutput;
        Double initSpeed, angle, timeAfterLaunch, rockDistance, rockWidth, rockHeight;
        String rockDistanceDescription, rockTimeDescription,
                        rockSpeedDescription;
        Double gravity = 9.81; // Aceleração da gravidade em m/s²
        Boolean alternative = false;
        // Construtor

        Lancamento_Pedra_Rochedo() {

                // cria a caixa da calculadora
                frame = new JFrame("Calculadora de Lançamento de Pedra");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(430, 400);
                frame.setLayout(null);

                // Cria os títulos e input para as variáveis

                // Velocidade inicial
                initSpeedText = createLabel("Velocidade (m/s):", 40, 25, 160, 30);
                initSpeedInput = createTextField("0", 190, 25, 70, 30, true, true);

                // Angulo
                angleText = createLabel("Angulo (graus):", 40, 60, 160, 30);
                angleInput = createTextField("0", 190, 60, 70, 30, true, true);

                // Altura do Rochedo
                rockHeightText = createLabel("Altura do Rochedo (m):", 40, 95, 160, 30);
                rockHeightInput = createTextField("0", 190, 95, 70, 30, true, true);

                // Tempo ate a pedra atingir o solo
                timeText = createLabel("Tempo (s):", 40, 130, 160, 30);
                timeAfterLaunchInput = createTextField("0", 190, 130, 70, 30, true, true);

                // comprimento do rochedo
                rockWidthtText = createLabel("Comprimento do rochedo (m):", 40, 165, 160, 30);
                rockWidthInput = createTextField("0", 190, 165, 70, 30, true, true);
                rockWidthInput.setVisible(false);
                rockWidthtText.setVisible(false);

                // distancisa do rochedo
                rockDistanceText = createLabel("Distância do rochedo (m):", 40, 200, 160, 30);
                distanceRockInput = createTextField("0", 190, 200, 70, 30, true, true);
                distanceRockInput.setVisible(false);
                rockDistanceText.setVisible(false);

                // Check Bom para verificar se a pedra bateu no rochedo caiu antes ou passou
                // pelo rochedo
                checkBtn = new JButton("Alternar");
                checkBtn.setBounds(290, 130, 100, 30);
                checkBtn.addActionListener(this);
                checkBtn.setFocusable(false);

                // Botão para calcular
                calcBtn = new JButton("Calcular");
                calcBtn.setBounds(290, 60, 100, 30);
                calcBtn.addActionListener(this);
                calcBtn.setFocusable(false);

                // Cria as variáveis de saída

                // Velocidade da pedra
                rockSpeedDescription = "Velocidade da pedra (m/s): ";
                initSpeedOutput = createTextField(rockSpeedDescription, 40, 290, 350, 30, false, false);

                // Botão para resetar
                resetBtn = new JButton("Reset");
                resetBtn.setBounds(190, 330, 80, 30);
                resetBtn.addActionListener(this);
                resetBtn.setFocusable(false);

                // Adicionar elementos ao frame para mostrar na tela
                frame.add(initSpeedText);
                frame.add(initSpeedInput);
                frame.add(angleText);
                frame.add(angleInput);
                frame.add(rockHeightText);
                frame.add(rockHeightInput);
                frame.add(timeText);
                frame.add(timeAfterLaunchInput);
                frame.add(rockWidthtText);
                frame.add(rockWidthInput);
                frame.add(rockDistanceText);
                frame.add(distanceRockInput);
                frame.add(calcBtn);
                frame.add(resetBtn);
                frame.add(checkBtn);
                frame.setVisible(true);

        }

        public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat("#.00");
                // Executa a função do botão "Calcular"
                if (e.getSource() == calcBtn) {
                        try {
                                // Lê os valores de entrada
                                initSpeed = Double.parseDouble(initSpeedInput.getText());
                                angle = Double.parseDouble(angleInput.getText());
                                rockHeight = Double.parseDouble(rockHeightInput.getText());
                                timeAfterLaunch = Double.parseDouble(timeAfterLaunchInput.getText());

                                // Verifica se os valores são positivos
                                if (initSpeed < 0 || angle < 0 || rockHeight < 0 || timeAfterLaunch < 0) {
                                        JOptionPane.showMessageDialog(frame,
                                                        "Por favor, insira valores positivos.");
                                        return;

                                }
                                if (angle < 0 || angle > 89) {
                                        JOptionPane.showMessageDialog(frame,
                                                        "O ângulo deve estar entre 1 e 89 graus.");
                                        return;
                                }
                                if (angle == 0) {
                                        // Verifica se o ângulo está dentro do intervalo válido
                                        if (initSpeed == 0 || rockHeight == 0 || timeAfterLaunch == 0) {
                                                JOptionPane.showMessageDialog(frame,
                                                                "Para descobrir o angulo é nescessário que tenha os dados da velocidade, altura do rochedo e o tempo que a pedra toca no topo do rochedo");
                                                return;
                                        } else {
                                                // Calcula o ângulo
                                                angle = (rockHeight + 0.5 * gravity * timeAfterLaunch
                                                                * timeAfterLaunch)
                                                                / (initSpeed * timeAfterLaunch);

                                                angle = Math.asin(angle);
                                                // Converte o ângulo de radianos para graus
                                                angle = Math.toDegrees(angle);

                                                angleInput.setText(String.format("%.2f", String.valueOf(angle)));

                                        }

                                }

                                if (timeAfterLaunch == 0) {
                                        // Verifica se o ângulo está dentro do intervalo válido
                                        if (initSpeed == 0 || rockHeight == 0 || angle == 0) {
                                                JOptionPane.showMessageDialog(frame,
                                                                "Para descobrir o tempo de queda é nescessário que tenha os dados da velocidade, altura do rochedo e o angulo");
                                                return;
                                        } else {
                                                // Calcula o tempo de queda
                                                double a = gravity * 0.5;
                                                double b = -initSpeed * Math.sin(Math.toRadians(angle));
                                                double c = rockHeight;
                                                double delta = Math.pow(b, 2) - 4 * a * c;
                                                if (delta < 0) {
                                                        JOptionPane.showMessageDialog(frame,
                                                                        "Não há solução real para os dados fornecidos.");
                                                        return;
                                                }
                                                // Calcula o tempo de queda
                                                timeAfterLaunch = (-b + Math.sqrt(delta)) / (2 * a);

                                                System.out.println("Tempo de queda: " + timeAfterLaunch);
                                                // Atualiza o campo de saída

                                                timeAfterLaunchInput.setText(df.format(timeAfterLaunch));

                                        }
                                }

                                if (rockHeight == 0) {
                                        // Verifica se o ângulo está dentro do intervalo válido
                                        if (initSpeed == 0 || angle == 0 || timeAfterLaunch == 0) {
                                                JOptionPane.showMessageDialog(frame,
                                                                "Para descobrir a altura do rochedo é nescessário que tenha os dados da velocidade, angulo e o tempo que a pedra toca no topo do rochedo");
                                                return;
                                        } else {
                                                // Calcula a altura do rochedo
                                                rockHeight = initSpeed * Math.sin(Math.toRadians(angle))
                                                                * timeAfterLaunch
                                                                - (0.5 * gravity
                                                                                * timeAfterLaunch
                                                                                * timeAfterLaunch);

                                                System.out.println("Altura do rochedo: " + rockHeight);
                                                // Atualiza o campo de saída

                                                rockHeightInput.setText(df.format(rockHeight));

                                        }

                                }

                                if (initSpeed == 0) {
                                        // Verifica se o ângulo está dentro do intervalo válido
                                        if (rockHeight == 0 || angle == 0 || timeAfterLaunch == 0) {
                                                JOptionPane.showMessageDialog(frame,
                                                                "Para descobrir a velocidade inicial é nescessário que tenha os dados da altura do rochedo, angulo e o tempo que a pedra toca no topo do rochedo");
                                                return;
                                        } else {
                                                // Calcula a velocidade inicial
                                                initSpeed = (rockHeight + 0.5 * gravity * timeAfterLaunch
                                                                * timeAfterLaunch)
                                                                / (timeAfterLaunch * Math.sin(Math.toRadians(angle)));

                                                System.out.println("Velocidade inicial: " + initSpeed);
                                                // Atualiza o campo de saída

                                        }

                                }
                                rockHeightInput.setVisible(false);

                        } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame,
                                                "Por favor, insira valores válidos." + ex.getMessage());
                                ex.printStackTrace();
                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame,
                                                "Erro inesperado: " + ex.getMessage());
                                ex.printStackTrace();
                        }
                }

                // Executa a função do botão "Reset"
                if (e.getSource() == resetBtn) {
                        initSpeedInput.setText("0");
                        angleInput.setText("0");
                        rockHeightInput.setText("0");
                        rockHeightInput.setText("0");
                        timeAfterLaunchInput.setText("0");
                        initSpeedOutput.setText("0");
                        rockWidthInput.setText("0");
                        distanceRockInput.setText("0");
                }

                // Executa a função do botão "Alternar"
                if (e.getSource() == checkBtn) {

                        if (alternative) {
                                alternative = false;
                                timeText.setVisible(true);
                                timeAfterLaunchInput.setVisible(true);
                                rockWidthInput.setVisible(false);
                                rockWidthtText.setVisible(false);
                                rockDistanceText.setVisible(false);
                                distanceRockInput.setVisible(false);
                        } else {
                                alternative = true;
                                timeText.setVisible(false);
                                timeAfterLaunchInput.setVisible(false);
                                rockWidthInput.setVisible(true);
                                rockWidthtText.setVisible(true);
                                rockDistanceText.setVisible(true);
                                distanceRockInput.setVisible(true);
                                rockWidthInput.setText("0");
                                distanceRockInput.setText("0");
                        }

                }

        }

        public static void main(String[] args) {
                new Lancamento_Pedra_Rochedo();
        }

        private JLabel createLabel(String text, int x, int y, int width, int height) {
                JLabel label = new JLabel(text);
                label.setBounds(x, y, width, height);
                return label;
        }

        private JTextField createTextField(String text, int x, int y, int width, int height, boolean editable,
                        boolean visible) {
                JTextField textField = new JTextField(text);
                textField.setBounds(x, y, width, height);
                textField.setEditable(editable);
                textField.setVisible(visible);
                return textField;
        }
}
