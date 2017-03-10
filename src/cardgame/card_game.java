package cardgame;

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Robot;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class card_game extends JFrame implements ActionListener {

	Random rnd = new Random();

	static int btn_del = 0, btn_dele = 0;// ������ ��ư�� ��ȣ�� ����
	static int i = 0, num[] = new int[16];
	static int fc, sc, click_count = 0, cor_count;// ù��° Ŭ��, �ι�° Ŭ��, Ŭ�� Ƚ��, ����
	static JFrame frm = new JFrame();
	static JButton btn[] = new JButton[16];
	static JLabel image_label[] = new JLabel[16];

	static int click_stop = 0; // Ŭ�� ���� �� �ٸ� ��ư�� Ŭ���� �ȵǰ� ��
	static int sec_time = 0; // 1�� ����
	static int dec_time = 0; // 0.1�� ����
	static int stop_time=-100; // ��ư�̳� �̹����� 1���Ŀ� ������� �� ����
	static int timer_stop; // Ÿ�̸Ӹ� ������ ����

	static Timer timer = new Timer();

	public card_game() {
		frm.setBounds(600, 150, 812, 838); // ������ ��ġ, ũ�� ����
		frm.setLayout(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (i = 0; i < 16; i++) // �ߺ� �˻�
		{
			num[i] = rnd.nextInt(16);
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					if (num[i] == num[j]) {
						i--;
						break;
					}
				}
			}
		}

		for (i = 0; i < 16; i++) {
			frm.add(btn[i] = new JButton("")); // �����Ӿȿ� ��ư�� ����

			btn[i].addActionListener(this); // ��ư�� �̺�Ʈ�� ����ϰڴ�
			btn[i].setSize(200, 200);
			if (i < 4)
				btn[i].setLocation(0 + 200 * (i), 0);
			else if (i < 8)
				btn[i].setLocation(0 + 200 * (i - 4), 200);
			else if (i < 12)
				btn[i].setLocation(0 + 200 * (i - 8), 400);
			else if (i < 16)
				btn[i].setLocation(0 + 200 * (i - 12), 600);
		}

		for (i = 0; i < 16; i++) {
			if (num[i] == 0 || num[i] == 15)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image8.png")));
			else if (num[i] == 1 || num[i] == 14)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image1.png")));
			else if (num[i] == 2 || num[i] == 13)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image2.png")));
			else if (num[i] == 3 || num[i] == 12)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image3.png")));
			else if (num[i] == 4 || num[i] == 11)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image4.png")));
			else if (num[i] == 5 || num[i] == 10)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image5.png")));
			else if (num[i] == 6 || num[i] == 9)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image6.png")));
			else if (num[i] == 7 || num[i] == 8)
				frm.add(image_label[i] = new JLabel(new ImageIcon("image/image7.png")));

			image_label[i].setSize(200, 200);

			if (i < 4)
				image_label[i].setLocation(0 + 200 * (i), 0);
			else if (i < 8)
				image_label[i].setLocation(0 + 200 * (i - 4), 200);
			else if (i < 12)
				image_label[i].setLocation(0 + 200 * (i - 8), 400);
			else if (i < 16)
				image_label[i].setLocation(0 + 200 * (i - 12), 600);
			image_label[i].setVisible(false);
		}

		frm.setVisible(true); // �������� ���� �ְ� ����
	}

	public static void finish() {
		JFrame ffrm = new JFrame("��������");
		ffrm.setBounds(700, 400, 300, 200);
		ffrm.setVisible(true);
		ffrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel finish = new JLabel(sec_time+"�� �ɷȽ��ϴ�.", JLabel.CENTER);

		ffrm.add(finish);
	}

	public static void main(String[] args) {

		timer.schedule(new WorkTask(), 0, 100); // Ÿ�̸�. 0.1�ʸ��� �����

		new card_game();
	}

	public static class WorkTask extends TimerTask {
		@Override
		public void run() {
			dec_time++;
			if (dec_time % 10 == 0) {
				sec_time++;
			}

			if (stop_time + 10 == dec_time) // ��ư Ŭ�� �� 1�ʰ� ������ ��ư�� �����ְ� �̹����� ���������
			{
				btn[btn_del].setVisible(true); // ��ư�� �ٽ� ������
				image_label[btn_del].setVisible(false); // �̹����� �ٽ� ���������
				btn[btn_dele].setVisible(true);
				image_label[btn_dele].setVisible(false);
				click_stop = 0;
			}
			
			if (timer_stop == 1)
				timer.cancel(); // Ÿ�̸� ����
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (click_stop != 1) // ���� �ٸ� ��ư�� Ŭ�� ���� �� ������� �ʰ���
		{
			for (i = 0; i < 16; i++) {
				if (e.getSource().equals(btn[i])) {

					if (click_count == 0) {
						btn_del = i;
						fc = num[i];
						click_count++;

						btn[btn_del].setVisible(false); // ��ư�� ������ �ʰ���
						image_label[btn_del].setVisible(true); // �̹����� ���̰� ��
					} else if (click_count == 1) // count�� �̿��Ͽ� �ι�° Ŭ���� ����
					{
						btn_dele = i;
						sc = num[i];
						click_count--;

						btn[btn_dele].setVisible(false); // ��ư�� ������ �ʰ���
						image_label[btn_dele].setVisible(true); // �̹����� ���̰� ��

						if (fc + sc == 15) {
							cor_count++;
						}

						else {

							/*
							 * try { Thread.sleep(500); } catch
							 * (InterruptedException e1) ��ư Ŭ���� 1�� ����. �̱��� {
							 * e1.printStackTrace(); }
							 */
							click_stop = 1;
							stop_time = dec_time;	//1�ʵ��� ��ư�� ���̰� ���� �ð�(0.1�� ����)�� ����
						}
					}
				}
			}
		}
		if (cor_count == 8) {
			frm.setVisible(false);
			timer_stop = 1; // Ÿ�̸� ���� ������ 1�� ����
			finish();
		}
	}
}
