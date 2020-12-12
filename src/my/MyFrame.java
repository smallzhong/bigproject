package my;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MyFrame extends JFrame
{
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/yc_data";

    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "123456";

    // 主布局，卡片
    JPanel cards = new JPanel();

    // 表格数据
    DefaultTableModel tableModel = new DefaultTableModel();

    // 显示表格
    JTable table = new JTable(tableModel);

    void testsql()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete from yc_villagers";
            stmt.execute(sql);

            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
    }

    void init()
    {

//        EditStudentDialog a = new EditStudentDialog(this);
//        a.setVisible(true);
//        Student s = a.getValue();

        // 设置表格为不可编辑
//        table.setEnabled(false);
    }

    // 测试函数
    void test()
    {
        Villager v = new Villager();
        v.village = "村庄3";
        v.name = "村庄3";
        v.sex = "村庄3";
        v.id = "123123123123";
        v.addr = "村庄3";
        v.phone_number = "村庄3";

        this.addOneData(v);
    }

    public MyFrame(String title)
    {
        super("8003119075 钟雨初");

        // 初始化
        init();

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        // 菜单 文件
        JMenu fileMenu = new JMenu("视图");
        menubar.add(fileMenu);
        JMenuItem fileOpenCmd = new JMenuItem("视图1");
        JMenuItem fileSaveCmd = new JMenuItem("视图2");
        JMenuItem fileSaveAsCmd = new JMenuItem("村民信息");
        fileMenu.add(fileOpenCmd);
        fileMenu.add(fileSaveCmd);
        fileMenu.add(fileSaveAsCmd);

        JMenuItem fileExitCmd = new JMenuItem("退出");
        fileMenu.addSeparator();
        fileMenu.add(fileExitCmd);

        // 帮助菜单
        JMenu operateMenu = new JMenu("操作");
        menubar.add(operateMenu);
        JMenuItem refreshVillageInfo = new JMenuItem("刷新");
        JMenuItem deleteSelectedItem = new JMenuItem("删除数据");
        JMenuItem addItem = new JMenuItem("增加数据");
        JMenuItem test2 = new JMenuItem("测试（暂未使用）");

        operateMenu.add(refreshVillageInfo);
        operateMenu.add(test2);
        operateMenu.add(deleteSelectedItem);
        operateMenu.add(addItem);

        // 测试按钮
        test2.addActionListener(e ->
        {
//            test();
//            EditVillagerDialog ttt = new EditVillagerDialog(this);
//            if (ttt.exec() == true)
//                System.out.println("true");
//            else
//                System.out.println("false");
//
//            Student ret = ttt.getValue();
//            System.out.println(ret);

        });

        // 添加数据
        addItem.addActionListener(e ->
        {
            EditVillagerDialog villagerDialog = new EditVillagerDialog(this);

            // 如果用户点击了取消或者关闭了窗口，则直接返回
            if (villagerDialog.exec() == false)
                return;

            else
            {
                Villager v = villagerDialog.getValue();

                // 插入数据
                if (addOneData(v) == true)
                {
                    UpdateVillagerData();
                    JOptionPane.showMessageDialog(null,
                            v.name + "的信息插入成功！",
                            "错误", JOptionPane.INFORMATION_MESSAGE);

                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "插入数据出错！请检查数据有效性！", "错误", JOptionPane.INFORMATION_MESSAGE);
                }
            }
//            Connection conn = null;
//            Statement stmt = null;
//            // 准备语句
//            PreparedStatement ps = null;
//
//            try
//            {
//                Class.forName(JDBC_DRIVER);
//                conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                stmt = conn.createStatement();
//
//
//                // 写sql语句
//                String sql = "insert into yc_villagers (village, name, sex, id, addr, phone_number) values(?, ?, ?, ?, ?, ?)";
//                ps = conn.prepareStatement(sql);
//                ps.setString(1, "村庄3");
//                ps.setString(2, "村庄3");
//                ps.setString(3, "村庄3");
//                ps.setString(4, "村庄3");
//                ps.setString(5, "村庄3");
//                ps.setString(6, "村庄3");
//
//                // 执行update操作
//                int resultSet = ps.executeUpdate();
//                System.out.printf("resultset = %d\n", resultSet);
//                if (resultSet > 0)
//                    System.out.println("success");
//                else
//                    System.out.println("failure");
//
//                stmt.close();
//                conn.close();
//            } catch (SQLException se)
//            {
//                // 处理 JDBC 错误
//                JOptionPane.showMessageDialog(null,
//                        "插入数据错误！两个人的身份证号不能一致，请检查您的输入！",
//                        "出错啦", JOptionPane.INFORMATION_MESSAGE);
//                se.printStackTrace();
//            } catch (Exception eee)
//            {
//                // 处理 Class.forName 错误
//                eee.printStackTrace();
//            } finally
//            {
//                // 关闭资源
//                try
//                {
//                    if (stmt != null) stmt.close();
//                } catch (SQLException se2)
//                {
//                }// 什么都不做
//                try
//                {
//                    if (conn != null) conn.close();
//                } catch (SQLException se)
//                {
//                    se.printStackTrace();
//                }
//            }
//
//            // 插入数据之后更新表格
//            UpdateVillagerData();
        });


        // 删除数据
        deleteSelectedItem.addActionListener(e ->
        {
            if (table.getSelectedRows().length == 0)
            {
                JOptionPane.showMessageDialog(null,
                        "您并未选中任何一行数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 弹出对话框确认
            int select = JOptionPane.showConfirmDialog(this,
                    "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                    "确认", JOptionPane.YES_NO_OPTION);
            if (select != 0) return; // 0号按钮是'确定'按钮

            Connection conn = null;
            Statement stmt = null;
            try
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                int[] count = table.getSelectedRows(); // 获取你选中的行号（记录）
                for (int i = 0; i < count.length; i++)
                {
                    // 读取身份证号字段的值
                    String id = table.getValueAt(count[i], 3).toString();
                    System.out.println(id);

                    String sql = "delete from yc_villagers where id = \"" + id + "\";";
                    System.out.printf("%s 被执行\n", sql);
                    stmt.execute(sql);
                }

                stmt.close();
                conn.close();
            } catch (SQLException se)
            {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception eee)
            {
                // 处理 Class.forName 错误
                eee.printStackTrace();
            } finally
            {
                // 关闭资源
                try
                {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2)
                {
                }// 什么都不做
                try
                {
                    if (conn != null) conn.close();
                } catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }


            // TODO：删除后要更新数据
            UpdateVillagerData();
        });

        // 更新村民数据（重新执行查询）
        refreshVillageInfo.addActionListener(e -> UpdateVillagerData());

        // 删除数据操作
//        deleteData.addActionListener(e -> onDelete());

        // 退出事件响应
        fileExitCmd.addActionListener(e -> System.exit(0));
        // 切换到村民信息视图
        fileOpenCmd.addActionListener(e -> switchCard(1));
        fileSaveCmd.addActionListener(e -> switchCard(0));
        fileSaveAsCmd.addActionListener(e -> switchCard(2));
//        fileOpenCmd.addActionListener(e ->
//                JOptionPane.showMessageDialog(null, "钟雨初", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE));

        Container contentPane = getContentPane();

        // 给顶层容器，设置 BorderLayout
        contentPane.setLayout(new BorderLayout());
        contentPane.add(cards, BorderLayout.CENTER);

        // 两张卡片
        JPanel p1 = panel1();
        JPanel p2 = panel2();
        JPanel p3 = panel3();

        cards.setLayout(new CardLayout());
        cards.add(p1, "buttons"); // 添加第一张卡片, 名字叫 buttons
        cards.add(p2, "text"); // 添加第二张卡片，名字叫 text
        cards.add(p3, "table");

        switchCard(1);
        switchCard(0);
        switchCard(2);
    }

    // 往村民数据库中增加一条数据
    private boolean addOneData(Villager v)
    {
        Connection conn = null;
        Statement stmt = null;
        // 准备语句
        PreparedStatement ps = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();


            // 写sql语句
            String sql = "insert into yc_villagers (village, name, sex, id, addr, phone_number) values(?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, v.village);
            ps.setString(2, v.name);
            ps.setString(3, v.sex);
            ps.setString(4, v.id);
            ps.setString(5, v.addr);
            ps.setString(6, v.phone_number);

            // 执行update操作
            int resultSet = ps.executeUpdate();
            System.out.printf("resultset = %d\n", resultSet);
            if (resultSet > 0)
                System.out.println("success");
            else
                System.out.println("failure");

            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            JOptionPane.showMessageDialog(null,
                    "插入数据错误！两个人的身份证号不能一致，请检查您的输入！",
                    "出错啦", JOptionPane.INFORMATION_MESSAGE);
            se.printStackTrace();
            return false;
        } catch (Exception eee)
        {
            // 处理 Class.forName 错误
            eee.printStackTrace();
            return false;
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
                return false;
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
                return false;
            }
        }

        // 插入数据之后更新表格
        UpdateVillagerData();

        // TODO:这里要判断是否成功，失败则返回false
        return true;
    }

    // 往村民数据库中一次添加多条数据
    private boolean addMultipleData()
    {
        return true;
    }

    JPanel panel3()
    {
        // Content Pane
        JPanel root = new JPanel();
//        this.setContentPane(root); // 设置为默认背景
        root.setLayout(new BorderLayout());

        // 添加到主界面
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        root.add(scrollPane, BorderLayout.CENTER);

        // 初始化设置：添加5列
        tableModel.addColumn("村庄");
        tableModel.addColumn("姓名");
        tableModel.addColumn("性别");
        tableModel.addColumn("身份证号");
        tableModel.addColumn("地址");
        tableModel.addColumn("电话号码");

        setVillagerTableInfo();

        // 添加表格的右键响应事件
        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                    showContextMenu(e);
            }
        });

        return root;
    }

    void showContextMenu(MouseEvent e)
    {
        // 弹出右键菜单
        JPopupMenu menu = new JPopupMenu();
        JMenuItem detailMenuCmd = new JMenuItem("删除选中项");
        menu.add(detailMenuCmd);
        detailMenuCmd.addActionListener(ee ->
        {
            if (table.getSelectedRows().length == 0)
            {
                JOptionPane.showMessageDialog(null,
                        "您并未选中任何一行数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 弹出对话框确认
            int select = JOptionPane.showConfirmDialog(this,
                    "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                    "确认", JOptionPane.YES_NO_OPTION);
            if (select != 0) return; // 0号按钮是'确定'按钮

            Connection conn = null;
            Statement stmt = null;
            try
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                int[] count = table.getSelectedRows(); // 获取你选中的行号（记录）
                for (int i = 0; i < count.length; i++)
                {
                    // 读取身份证号字段的值
                    String id = table.getValueAt(count[i], 3).toString();
                    System.out.println(id);

                    String sql = "delete from yc_villagers where id = \"" + id + "\";";
                    System.out.printf("%s 被执行\n", sql);
                    stmt.execute(sql);
                }

                stmt.close();
                conn.close();
            } catch (SQLException se)
            {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception eee)
            {
                // 处理 Class.forName 错误
                eee.printStackTrace();
            } finally
            {
                // 关闭资源
                try
                {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2)
                {
                }// 什么都不做
                try
                {
                    if (conn != null) conn.close();
                } catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }

            UpdateVillagerData();
        });

        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    void setVillagerTableInfo()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM yc_villagers";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village = rs.getString("village");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String id = rs.getString("id");
                String addr = rs.getString("addr");
                String phone_number = rs.getString("phone_number");

                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village);
                rowData.add(name);
                rowData.add(sex);
                rowData.add(id);
                rowData.add(addr);
                rowData.add(phone_number);
                tableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
    }

    private void onDelete()
    {
        // 获取选中的行的索引
        int[] rows = table.getSelectedRows();
        if (rows.length == 0) return;

        // 弹出对话框确认

        int select = JOptionPane.showConfirmDialog(this,
                "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                "确认", JOptionPane.YES_NO_OPTION);
        if (select != 0) return; // 0号按钮是'确定'按钮

        // 技巧：从后往前删除
        for (int i = rows.length - 1; i >= 0; i--)
        {
            tableModel.removeRow(rows[i]);
        }
    }

    void UpdateVillagerData()
    {
        // 先把表格中全部的数据删除，避免重复数据出现
        int rows_ct = table.getRowCount();
        // 从后往前删除，防止下标变化导致删除失败
        for (int i = rows_ct - 1; i >= 0; i--)
        {
            tableModel.removeRow(i);
        }

        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM yc_villagers";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village = rs.getString("village");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String id = rs.getString("id");
                String addr = rs.getString("addr");
                String phone_number = rs.getString("phone_number");

                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village);
                rowData.add(name);
                rowData.add(sex);
                rowData.add(id);
                rowData.add(addr);
                rowData.add(phone_number);
                tableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
    }

    void addTableRow(Villager item)
    {
        Vector<Object> rowData = new Vector<>();
        rowData.add(item.village);
        rowData.add(item.name);
        rowData.add(item.sex);
        rowData.add(item.id);
        rowData.add(item.addr);
        rowData.add(item.phone_number);
        tableModel.addRow(rowData);
    }


    JPanel panel1()
    {
        // 创建第一个面板
        JPanel p1 = new JPanel();
        p1.add(new JButton("红"));
        p1.add(new JButton("绿"));
        p1.add(new JButton("蓝"));

        return p1;
    }

    JPanel panel2()
    {
        // 创建第二个面板
        JPanel p2 = new JPanel();
        p2.add(new JLabel("输入"));
        p2.add(new JTextField(20));
        return p2;
    }

    // 切换到cardnum卡片
    public void switchCard(int cardnum)
    {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        if (cardnum == 1)
        {
            cardLayout.show(cards, "buttons");
        }
        else if (cardnum == 0)
        {
            cardLayout.show(cards, "text");
        }
        else if (cardnum == 2)
        {
            cardLayout.show(cards, "table");
        }
    }
}