package com.luu;
// 导入必要的类
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;

// 文件加密器类
public class FileEncryptor {
    private static final String AES = "AES";// 指定加密算法为 AES
    private static final String PASSWORD = "your_password_here"; // 指定加密密码，用户需要替换成自己的密码

    public static void main(String[] args) {
        // 创建文件加密窗口
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new FileEncryptorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    // 获取 SecretKey 对象的方法
    private static SecretKey getSecretKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8"); // 将密码转换为字节数组
        MessageDigest sha = MessageDigest.getInstance("SHA-1"); // 创建 SHA-1 消息摘要
        key = sha.digest(key); // 对密码进行哈希摘要
        key = java.util.Arrays.copyOf(key, 16); // 截取前 16 个字节作为密钥字节数组
        return new SecretKeySpec(key, AES); // 返回密钥的 SecretKey 对象
    }

    // 文件加密的方法
    public static void encryptFile(File inputFile, File outputFile) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile); // 创建输入流对象
        FileOutputStream outputStream = new FileOutputStream(outputFile); // 创建输出流对象
        SecretKey secretKey = getSecretKey(PASSWORD); // 获取 SecretKey 对象
        Cipher cipher = Cipher.getInstance(AES); // 创建 Cipher 对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 初始化 Cipher 对象为加密模式

        byte[] inputBytes = new byte[(int) inputFile.length()]; // 创建与输入文件相同大小的字节数组
        inputStream.read(inputBytes); // 读取输入文件到字节数组

        byte[] outputBytes = cipher.doFinal(inputBytes); // 对字节数组进行加密操作
        outputStream.write(outputBytes); // 将加密后的字节数组写入输出文件

        inputStream.close(); // 关闭输入流
        outputStream.close(); // 关闭输出流
    }


    public static void decryptFile(File inputFile, File outputFile) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile); // 创建输入流对象
        FileOutputStream outputStream = new FileOutputStream(outputFile); // 创建输入流对象
        SecretKey secretKey = getSecretKey(PASSWORD);// 获取 SecretKey 对象
        Cipher cipher = Cipher.getInstance(AES);// 创建 Cipher 对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey);// 创建 Cipher 对象

        byte[] inputBytes = new byte[(int) inputFile.length()]; // 创建与输入文件相同大小的字节数组
        inputStream.read(inputBytes); // 读取输入文件到字节数组

        byte[] outputBytes = cipher.doFinal(inputBytes); // 对字节数组进行解密操作
        outputStream.write(outputBytes); // 将解密后的字节数组写入输出文件

        inputStream.close(); // 关闭输入流
        outputStream.close(); // 关闭输出流
    }
}


    // 文件加密器窗口类
    class FileEncryptorFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;// 窗口默认宽度
    private static final int DEFAULT_HEIGHT = 200; // 窗口默认高度

    public FileEncryptorFrame() {
        setTitle("File Encryptor"); // 设置窗口标题
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); // 设置窗口大小
        setLayout(new BorderLayout()); // 设置窗口布局为边框布局
        JButton encryptButton = new JButton("Encrypt Files"); // 创建加密文件按钮
        JButton decryptButton = new JButton("Decrypt Files"); // 创建解密文件按钮

        add(encryptButton, BorderLayout.WEST); // 将加密文件按钮添加到窗口左侧
        add(decryptButton, BorderLayout.EAST); // 将解密文件按钮添加到窗口右侧

        // 为加密文件按钮添加点击事件
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // 创建文件选择对话框
                fileChooser.setMultiSelectionEnabled(true); // 允许选择多个文件
                int returnValue = fileChooser.showOpenDialog(null); // 显示文件选择对话框
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles(); // 获取选择的文件
                    for (File file : selectedFiles) { // 遍历选择的文件
                        try {
                            File encryptedFile = new File(file.getParent(), file.getName() + ".enc"); // 创建加密文件对象
                            FileEncryptor.encryptFile(file, encryptedFile); // 对所选文件进行加密
                            JOptionPane.showMessageDialog(null, "Encrypted: " + encryptedFile.getPath()); // 显示加密完成提示框
                        } catch (Exception ex) { // 加密出现异常时
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Encryption failed: " + file.getPath()); // 显示加密失败提示框
                        }
                    }
                }
            }
        });


        // 为解密文件按钮添加点击事件
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // 创建文件选择对话框
                fileChooser.setMultiSelectionEnabled(true); // 允许选择多个文件
                int returnValue = fileChooser.showOpenDialog(null); // 显示文件选择对话框
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles(); // 获取选择的文件
                    for (File file : selectedFiles) { // 遍历选择的文件
                        try {
                            File decryptedFile = new File(file.getParent(), file.getName().replace(".enc", "")); // 创建解密文件对象
                            FileEncryptor.decryptFile(file, decryptedFile); // 对所选文件进行解密
                            JOptionPane.showMessageDialog(null, "Decrypted: " + decryptedFile.getPath()); // 显示解密完成提示框
                        } catch (Exception ex) { // 解密出现异常时
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Decryption failed: " + file.getPath()); // 显示解密失败提示框
                        }
                    }
                }
            }
        });
    }
    }