package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.model.BankBalanceVO;

public class BankService {

	List<BankBalanceVO> bbList;
	FileReader fileReader;
	BufferedReader buffer;
	Scanner scan;

	public BankService(String str) throws FileNotFoundException {
		fileReader = new FileReader(str);
		buffer = new BufferedReader(fileReader);
		bbList = new ArrayList<BankBalanceVO>();
		scan = new Scanner(System.in);
	}

	public void readBalance() throws IOException {
		String reader = "";
		while (true) {
			reader = buffer.readLine();
			if (reader == null)
				break;

			String[] balance = reader.split(":");

			String acc = balance[0];
			int money = Integer.valueOf(balance[1]);
			String date = balance[2];

			BankBalanceVO vo = new BankBalanceVO();
			vo = new BankBalanceVO();
			vo.setAcc(acc);
			vo.setBalance(money);
			vo.setDate(date);

			bbList.add(vo);

		}

	}

	/*
	 * balanceList 에서 계좌번호 0001인 데이터를 찾고 그 계좌의 현 잔액을 콘솔에 표시
	 */
	public BankBalanceVO pickAcc(String accNum) {

		int index = 0;
		int size = bbList.size();
		BankBalanceVO vo = null;

		for (index = 0; index < size; index++) {
			vo = bbList.get(index);
			if (vo.getAcc().equals(accNum)) {
				return vo;
			}
		}
		return null;

	}

	/*
	 * 계좌번호로 pickAcc로부터 vo값을 추출하고 balance값과 money값을 더하여 vo의 balance에 저장하고 콘솔에 보여주는
	 * 코드
	 */
	public void inputMoney(String accNum, int money) {

//		for(BankBalanceVO vo : bbList) {
//			System.out.println(vo);
//		}

		BankBalanceVO vo = pickAcc(accNum);
		int bal = vo.getBalance();
		vo.setBalance(bal + money);

		// java 1.7이하에서 지금도 사용하는 코드
		// 현재 컴퓨터 날짜값을 가져오기
		Date date = new Date(System.currentTimeMillis());

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = sf.format(date);

		vo.setDate(curDate);

		// java 1.8(8) 이상에서 사용하는 새로운 날짜
		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString());
		System.out.println(vo);
//		for(BankBalanceVO vv : bbList) {
//			System.out.println(vv);
//		}

	}

	public void outputMoney(String accNum, int money) {

		BankBalanceVO vo = pickAcc(accNum);
		int bal = vo.getBalance();
		
		if (bal < money) {
			System.out.println("잔액부족!!!!");
			return;
		}
		vo.setBalance(bal - money);

	}

	public int selectMenu() {
		System.out.println("===================");
		System.out.println("1. 입금\t2. 출금\t-9.종료");
		System.out.println("===================");
		System.out.print("업무선택 >");
		String strMenu = scan.nextLine();

		int intMenu = 0;
		try {
			intMenu = Integer.valueOf(strMenu);
		} catch (Exception e) {
			// 오류 무시
		}

		return intMenu;
	}

}
