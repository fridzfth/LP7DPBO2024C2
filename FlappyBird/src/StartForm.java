import javax.swing.*;

public class StartForm extends JFrame {
    public StartForm() {
        setTitle("Start Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Mengubah layout menjadi BoxLayout agar teks dan tombol bisa ditampilkan secara vertikal

        JLabel startLabel = new JLabel("Are you ready to start your journey?"); // Menambah label dengan teks yang diminta
        startLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Pusatkan teks secara horizontal
        panel.add(startLabel);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT); // Pusatkan tombol secara horizontal
        startButton.addActionListener(e -> {
            dispose(); // Tutup form saat tombol ditekan
            startGame(); // Panggil method untuk memulai game
        });

        panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak vertikal antara label dan tombol
        panel.add(startButton);

        add(panel);

        setVisible(true);
    }

    private void startGame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);

    }
}
