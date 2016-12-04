package com.acorn.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.acorn.dao.MemberDao;
import com.acorn.dto.MemberDto;

/*
 * 	emp 테이블과 dept 테이블의 내용을 이용해서 사원 목록을
 * 
 * 	Swing 의 JTable 에 출력해 보세요
 * 
 * 	출력할 내용
 * 
 * 	사원번호, 사원이름, 급여, 입사일, 부서명, 부서의 위치
 */


public class EmpFrame extends JFrame implements ActionListener{
	
	JTextField inputEmpno,inputEname;
	JButton serchBtn,resetBtn,deleteBtn,updateBtn;
	DefaultTableModel model;
	JTable table;
	int subNum=0;
	//생성자
	public EmpFrame(){
		initUI();
	}
	
	//UI 초기화 하는 메소드
	public void initUI(){
		//레이아웃
		setLayout(new BorderLayout());
		//페널
		JPanel topPanel = new JPanel();
		//레이블
		JLabel label1=new JLabel("사원번호");
		JLabel label2=new JLabel("사원이름");
		//텍스트 필드
		inputEmpno=new JTextField(10);
		inputEname=new JTextField(10);
		
		resetBtn = new JButton("목록보기");
		serchBtn = new JButton("검색");
		updateBtn = new JButton("수정");
		deleteBtn = new JButton("삭제");
		
		resetBtn.addActionListener(this);
		serchBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		//패널에 컴포넌트 추가
		topPanel.add(label1);
		topPanel.add(inputEmpno);
		topPanel.add(label2);
		topPanel.add(inputEname);
		topPanel.add(resetBtn);
		topPanel.add(serchBtn);
		topPanel.add(updateBtn);
		topPanel.add(deleteBtn);
		
		//JTable 의 칼럼명
		String[] colNames={"사원번호", "사원이름", "급여", "입사일", "부서명", "부서의 위치"};
		//테이블에 연결할 모델 객체 생성
		model = new DefaultTableModel(colNames,0);
		//테이블
		table = new JTable();
		table.setModel(model);
		
		JScrollPane tablePanel = new JScrollPane(table);
		
		//패널을 프레임의 north 에 배치
		add(topPanel, BorderLayout.NORTH);
		//테이블을 프레임의 가운데 배치하기
		add(tablePanel, BorderLayout.CENTER);
		
		//회원 목록 출력하기
		displayMember();
		
		
		//기본설정
		setBounds(200,200,800,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	//메인 메소드 
	public static void main(String[] args) {
		new EmpFrame();
	}
	
	//회원 정보를 JTable 에 출력하는 메소드
	public void displayMember(){
		//일단 모두 지운다음
		model.setRowCount(0);
		//1. 회원목록을 얻어온다.
		List<MemberDto> list=MemberDao.getInstance().getList();
		//2. 반복문 돌면서 회원 한명한명의 정보를 JTable 에 출력한다.
		for (MemberDto tmp : list) {
			//Vector 객체에 순서대로 번호, 이름, 주소를 담는다.
			Vector vec=new Vector();
			vec.add(tmp.getEmpNo());
			vec.add(tmp.geteName());
			vec.add(tmp.getSal());
			vec.add(tmp.getHiredate());
			vec.add(tmp.getdName());
			vec.add(tmp.getLoc());
			//테이블 모델에 row 로 추가한다.
			model.addRow(vec);
		}
	}
	
	public class SubFrame extends JFrame implements ActionListener {
		
		JTextField inputEmpno,inputEname,inputSal,inputHiredate,inputDname,inputLoc;
		JButton uBtn;
		
		//생성자
		public SubFrame(){
			initUI();
		}
		
		//UI 초기화 하는 메소드
		public void initUI(){
			//레이아웃
			setLayout(new BorderLayout());
			//패널
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			
			//레이블
			JLabel label1=new JLabel("사원번호");
			JLabel label2=new JLabel("사원이름");
			JLabel label3=new JLabel("급여");
			JLabel label4=new JLabel("입사일");
			JLabel label5=new JLabel("부서명");
			JLabel label6=new JLabel("부서의 위치");
			
			
			//텍스트 필드
			inputEmpno=new JTextField(10);
			inputEname=new JTextField(10);
			inputSal=new JTextField(10);
			inputHiredate=new JTextField(10);
			inputDname=new JTextField(10);
			inputLoc=new JTextField(10);
			
			uBtn = new JButton("수정");
			
			//패널에 컴포넌트 추가
			panel1.add(label1);
			panel1.add(inputEmpno);
			panel1.add(label2);
			panel1.add(inputEname);
			panel1.add(label3);
			panel1.add(inputSal);
			panel2.add(label4);
			panel2.add(inputHiredate);
			panel2.add(label5);
			panel2.add(inputDname);
			panel2.add(label6);
			panel2.add(inputLoc);
			panel2.add(uBtn);
			
			// 패널을 프레임에 배치하기
			add(panel1,BorderLayout.NORTH);
			add(panel2,BorderLayout.CENTER);
			
			uBtn.addActionListener(this);
			
			//기본설정
			setBounds(200,500,800,120);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}

		// subFrame 버튼 이벤트
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == uBtn){
				MemberDto dto = new MemberDto();
				dto.setEmpNo(Integer.parseInt(inputEmpno.getText()));
				dto.seteName(inputEname.getText());
				dto.setSal(inputSal.getText());
				dto.setHiredate(inputHiredate.getText());
				System.out.println(inputHiredate.getText());
				dto.setdName(inputDname.getText());
				dto.setLoc(inputLoc.getText());
				MemberDao.getInstance().update(dto, subNum);
				
				JOptionPane.showMessageDialog(this, "수정완료!");
				setVisible(false);
				displayMember();
			}
			
		}
	}

	//EmpFrame 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resetBtn){
			displayMember();
		}
		else if(e.getSource() == serchBtn){
			model.setRowCount(0);
			
			String eNum = inputEmpno.getText();
			String eName = inputEname.getText();
			if(eName.isEmpty() && eNum.isEmpty()){
				JOptionPane.showMessageDialog(this, "입력값이 없어요!");
				displayMember();
			}else if(eName.isEmpty()){
				int empNo = Integer.parseInt(eNum);
				List<MemberDto> list = MemberDao.getInstance().getData(empNo);
				for (MemberDto tmp : list) {
					Vector vec=new Vector();
					vec.add(tmp.getEmpNo());
					vec.add(tmp.geteName());
					vec.add(tmp.getSal());
					vec.add(tmp.getHiredate());
					vec.add(tmp.getdName());
					vec.add(tmp.getLoc());
					//테이블 모델에 row 로 추가한다.
					model.addRow(vec);
				}
			}else if(!eName.isEmpty()){
				List<MemberDto> list = MemberDao.getInstance().getData(eName);
				for (MemberDto tmp : list) {
					Vector vec=new Vector();
					vec.add(tmp.getEmpNo());
					vec.add(tmp.geteName());
					vec.add(tmp.getSal());
					vec.add(tmp.getHiredate());
					vec.add(tmp.getdName());
					vec.add(tmp.getLoc());
					//테이블 모델에 row 로 추가한다.
					model.addRow(vec);
				}
			}
		}//serchBtn
		else if(e.getSource()==deleteBtn){
			//선택된 table row의 인덱스를 가져온다.
			int index =table.getSelectedRow();
			if(index==-1){
				JOptionPane.showMessageDialog(this, "삭제할 row를 선택하세요!");
				return; //메소드 종료
			}
			//삭제할 회원의 번호를 읽어온다.
			int num =(int)table.getValueAt(index, 0);
			//MemberDao 객체를 이용해서 삭제한다.
			MemberDao.getInstance().delete(num);
			JOptionPane.showMessageDialog(this, "삭제 했습니다.");
			displayMember();
		}//deleteBtn
		else if(e.getSource()==updateBtn){
			//선택된 table row의 인덱스를 가져온다.
			int index =table.getSelectedRow();
			if(index==-1){
				JOptionPane.showMessageDialog(this, "수정할 row를 선택하세요!");
				return; //메소드 종료
			}
			//수정할 회원의 번호를 읽어온다.
			int num =(int)table.getValueAt(index, 0);
			//MemberDao 객체를 이용해서 회원 한명의 정보를 가져온다.
			List<MemberDto> list =MemberDao.getInstance().getData(num);
			SubFrame sub =new SubFrame();			
			sub.inputEmpno.setText(""+list.get(0).getEmpNo());
			sub.inputEname.setText(list.get(0).geteName());
			sub.inputSal.setText(list.get(0).getSal());
			sub.inputHiredate.setText(list.get(0).getHiredate());
			sub.inputDname.setText(list.get(0).getdName());
			sub.inputLoc.setText(list.get(0).getLoc());
			
			//subFrame에서 사용할 empno
			subNum = list.get(0).getEmpNo();
			
		}//updateBtn
			inputEmpno.setText("");
			inputEname.setText("");
	}
	
	
}
