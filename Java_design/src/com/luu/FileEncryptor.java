package com.luu;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class FileEncryptor {
    private static final String AES = "AES"; // 使用 AES 算法加密
    private static final String PASSWORD = "your_password_here"; // 加密和解密时使用的密码

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new FileEncryptorFrame(); // 创建文件加密器界面
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭行为为退出程序
            frame.setVisible(true); // 显示窗口
        });
    }

    private static SecretKey getSecretKey() throws Exception {
        byte[] key = FileEncryptor.PASSWORD.getBytes(StandardCharsets.UTF_8); // 将密码转换成字节数组
        MessageDigest sha = MessageDigest.getInstance("SHA-1"); // 使用 SHA-1 算法生成散列值
        key = sha.digest(key); // 对密码的字节数组进行散列运算
        key = java.util.Arrays.copyOf(key, 16); // 取散列结果的前 16 个字节作为密钥
        return new SecretKeySpec(key, AES); // 使用密钥和 AES 算法生成 SecretKey 对象
    }

    public static void encryptFile(File inputFile, File outputFile, JProgressBar progressBar) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile); // 创建输入文件流
        FileOutputStream outputStream = new FileOutputStream(outputFile); // 创建输出文件流
        SecretKey secretKey = getSecretKey(); // 获取加密密钥
        Cipher cipher = Cipher.getInstance(AES); // 创建加密器
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 初始化加密器

        readFileToBytes(inputFile, progressBar, inputStream, outputStream, cipher);
    }

    private static void readFileToBytes(File inputFile, JProgressBar progressBar, FileInputStream inputStream, FileOutputStream outputStream, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] inputBytes = new byte[(int) inputFile.length()]; // 创建输入文件大小的字节数组
        inputStream.read(inputBytes); // 将输入文件的内容读入字节数组

        int totalBytes = inputBytes.length; // 文件总字节数
        int processedBytes = 0; // 已处理的字节数
        byte[] outputBytes; // 存储加密后的字节数组
        while (processedBytes < totalBytes) { // 如果还有剩余的未加密的字节
            int bytesToProcess = Math.min(totalBytes - processedBytes, 1024); // 每次加密 1024 字节
            outputBytes = cipher.update(inputBytes, processedBytes, bytesToProcess); // 加密一部分字节
            outputStream.write(outputBytes); // 将加密后的字节写入输出文件流
            processedBytes += bytesToProcess; // 更新已处理的字节数

            int progress = (int) ((double) processedBytes / totalBytes * 100); // 计算加密进度百分比
            progressBar.setValue(progress); // 更新进度条的值
        }
        outputBytes = cipher.doFinal(); // 加密剩余的字节
        outputStream.write(outputBytes); // 将加密后的字节写入输出文件流

        inputStream.close(); // 关闭输入文件流
        outputStream.close(); // 关闭输出文件流
    }

    public static void decryptFile(File inputFile, File outputFile, JProgressBar progressBar) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile); // 创建输入文件流
        FileOutputStream outputStream = new FileOutputStream(outputFile); // 创建输出文件流
        SecretKey secretKey = getSecretKey(); // 获取解密密钥
        Cipher cipher = Cipher.getInstance(AES); // 创建解密器
        cipher.init(Cipher.DECRYPT_MODE, secretKey); // 初始化解密器

        readFileToBytes(inputFile, progressBar, inputStream, outputStream, cipher);
    }
}

class FileEncryptorFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public FileEncryptorFrame() {
        setTitle("文件加解密器");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        JButton encryptButton = new JButton("加密文件");
        JButton decryptButton = new JButton("解密文件");

        add(encryptButton, BorderLayout.WEST); // 将加密按钮添加到窗口左侧
        add(decryptButton, BorderLayout.EAST); // 将解密按钮添加到窗口右侧

        JProgressBar progressBar = new JProgressBar(); // 创建进度条组件
        progressBar.setStringPainted(true); // 显示进度条的百分比文本
        add(progressBar, BorderLayout.SOUTH); // 将进度条添加到窗口底部

        encryptButton.addActionListener(e -> { // 当用户单击加密按钮时执行
            JFileChooser fileChooser = new JFileChooser(); // 创建文件选择器
            fileChooser.setMultiSelectionEnabled(true); // 允许选择多个文件
            int returnValue = fileChooser.showOpenDialog(null); // 显示文件选择对话框
            if (returnValue == JFileChooser.APPROVE_OPTION) { // 如果用户选择了文件
                File[] selectedFiles = fileChooser.getSelectedFiles(); // 获取用户选择的文件
                for (File file : selectedFiles) { // 遍历用户选择的每个文件
                    try {
                        File encryptedFile = new File(file.getParent(), file.getName() + ".enc"); // 创建加密后的文件对象
                        FileEncryptor.encryptFile(file, encryptedFile, progressBar); // 加密文件
                        JOptionPane.showMessageDialog(null, "Encrypted: " + encryptedFile.getPath()); // 显示加密成功的消息
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Encryption failed: " + file.getPath()); // 显示加密失败的消息
                    }
                }
            }
        });

        decryptButton.addActionListener(e -> { // 当用户单击解密按钮时执行
            JFileChooser fileChooser = new JFileChooser(); // 创建文件选择器
            fileChooser.setMultiSelectionEnabled(true); // 允许选择多个文件
            int returnValue = fileChooser.showOpenDialog(null); // 显示文件选择对话框
            if (returnValue == JFileChooser.APPROVE_OPTION) { // 如果用户选择了文件
                File[] selectedFiles = fileChooser.getSelectedFiles(); // 获取用户选择的文件
                for (File file : selectedFiles) { // 遍历用户选择的每个文件
                    try {
                        File decryptedFile = new File(file.getParent(), file.getName().replace(".enc", "")); // 创建解密后的文件对象
                        FileEncryptor.decryptFile(file, decryptedFile, progressBar); // 解密文件
                        JOptionPane.showMessageDialog(null, "Decrypted: " + decryptedFile.getPath()); // 显示解密成功的消息
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Decryption failed: " + file.getPath()); // 显示解密失败的消息
                    }
                }
            }
        });
    }
}


