package com.biz.bank.exec;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.biz.bank.model.BankBalanceVO;
import com.biz.bank.service.BankService;

public class BankExec_01 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = "src/balance.txt";
		BankService bs = null;
		try {
			//try내에서 다시 객체를 초기화해서
			//사용할 수 있도록
			bs = new BankService(str);
			bs.readBalance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			int intMenu = bs.selectMenu();
			
			if(intMenu == -9) break;
			
			System.out.print("계좌번호 >");
			String accNum = scan.nextLine();
			
			BankBalanceVO vo = bs.pickAcc(accNum);
			if(vo == null) {
				System.out.println("없는 계좌입니다");
				continue;
			}
		
			if(intMenu == 1) {
				// 입금
				System.out.print("입금금액 >");
				String inputMoney = scan.nextLine();
				int intinputMoney = Integer.valueOf(inputMoney);
				bs.inputMoney(accNum, intinputMoney);
			}
			if(intMenu == 2) {
				// 출금
				System.out.print("출금금액 >");
				String outputMoney = scan.nextLine();
				int intoutputMoney = Integer.valueOf(outputMoney);
				bs.outputMoney(accNum, intoutputMoney);
			}
			
			
			
			
			
//			else System.out.println(vo.getBalance());
		}
	}
}
