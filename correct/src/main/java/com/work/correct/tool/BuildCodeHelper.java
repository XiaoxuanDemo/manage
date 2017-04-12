package com.work.correct.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public enum BuildCodeHelper {
    INSTANCE;
	private final static char[] library = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };
	private static int width = 120;
	private static int height = 40;
	private static int fontSize = 22;
	private static int number = 4;
	private static int lineCounts = 80;
	private static int codeX = width / (number + 2);
	private static int codeY = height / 2 + fontSize / 3;
	private static Random random = new Random();
	private static Font font = BuildCodeHelper.INSTANCE.getFont();

	// 创建验证码
	public void buildCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OutputStream out = null;
		BufferedImage imageBuffer = null;
		try {
			int w, x, y, z;
			char code;
			imageBuffer = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics graphics = imageBuffer.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, height);
			graphics.setFont(font);
			/* 画出干扰线 */
			for (int i = 0; i < lineCounts; i++) {
				graphics.setColor(getRandomColor());
				x = random.nextInt(width);
				y = random.nextInt(height);
				w = random.nextInt(10);
				z = random.nextInt(8);
				graphics.drawLine(x, y, x + w, y + z);
			}
			/* 添加噪点 */
			float yawpRate = 0.01f;
			int area = (int) (yawpRate * width * height);
			for (int i = 0; i < area; i++) {
				x = random.nextInt(width);
				y = random.nextInt(height);
				imageBuffer.setRGB(x, y, random.nextInt(255));
			}
			/* 画出边框 */
			
			graphics.setColor(Color.BLACK);
			graphics.drawRect(0, 0, width - 1, height - 1);
			
			/* 画出验证码 */
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < number; i++) {
				code = library[random.nextInt(library.length)];
				stringBuffer.append(code);
				graphics.setColor(getRandomColor(40, 100));
				graphics.drawString(String.valueOf(code), (i + 1) * codeX,
						codeY);
			}
			/* 禁止图片缓存 */
			HttpSession session = request.getSession();
			session.setAttribute("code", stringBuffer.toString());
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			out = response.getOutputStream();
			ImageIO.write(imageBuffer, "jpeg", out);
		} finally {
			out.flush();
			out.close();
			imageBuffer = null;
		}

	}

	// 检查验证码是否输入正确
	public boolean check(HttpSession session, String inputCode) {
		boolean flag = false;
		String code = (String) session.getAttribute("code");
		if (inputCode != null) {
			if (inputCode.equalsIgnoreCase(code)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	// 给定范围创建随机颜色
	private Color getRandomColor(int min, int max) {
		if (min > 255)
			min = 255;
		if (max > 255)
			max = 255;
		int t = max - min;
		int red = min + random.nextInt(t);
		int green = min + random.nextInt(t);
		int bule = min + random.nextInt(t);
		return new Color(red, green, bule);
	}

	// 创建随机颜色
	private Color getRandomColor() {
		int red = random.nextInt(255);
		int green = random.nextInt(255);
		int bule = random.nextInt(255);
		return new Color(red, green, bule);
	}

	// 创建字体
	private Font getFont() {
		try {
			String path=BuildCodeHelper.class.getResource("/imageFont.ttf").getPath();
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			return font.deriveFont(Font.PLAIN, fontSize);
		} catch (Exception e) {
			return new Font("Arial", Font.PLAIN, fontSize);
		}
	}
}
