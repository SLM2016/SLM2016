package student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class StudentModel {

	// private final String INDEX_NAME = "name";
	// private final String INDEX_EMAIL = "email";
	// private final String INDEX_PHONE = "phone";
	// private final String INDEX_COMPANY = "company";
	// private final String INDEX_APARTMENT = "apartment";
	// private final String INDEX_TITLE = "title";
	// private final String INDEX_NICKNAME = "nickname";
	// private final String INDEX_VEGE_MEAT = "vegeMeat";
	// private final String INDEX_RECEIPT_TYPE = "receiptType";
	// private final String INDEX_RECEIPT_COMPANY_NAME_AND_EIN =
	// "receiptCompanyNameAndEIN";
	// private final String INDEX_TICKET_TYPE = "ticketType";
	// private final String INDEX_TEAM_MEMBERS = "teamMembers";
	// private final String INDEX_COMMENT = "comment";
	// private final String INDEX_TIMESTAMP = "timestamp";

	private String name = "";
	private String nickname = "";
	private String email = "";
	private String phone = "";
	private String company = "";
	private String apartment = "";
	private String title = "";
	private String ticketType = "";
	private String ticketPrice = "";
	private String receiptType = "";
	private String receiptCompanyName = "";
	private String fkCourseInfoId = "";

	public String getFkCourseInfoId() {
		return fkCourseInfoId;
	}

	public void setFkCourseInfoId(String fkCourseInfoId) {
		this.fkCourseInfoId = fkCourseInfoId;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	private String receiptCompanyEIN = "";
	private String teamMembers = "";
	private String comment = "";
	private String vegeMeat = "";
	private String timestamp = "";
	private String studentStatus = "";
	private String paymentStatus = "";
	private String receiptStatus = "";

	public StudentModel() {

	}

	// public StudentModel(Row row, Map<String, Integer> nameIndexMap) {
	// int timestampIndex = checkIndexExist(nameIndexMap, INDEX_TIMESTAMP);
	// if (timestampIndex != -1) {
	// Cell cell = row.getCell(timestampIndex);
	// this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	// .format(new Date(cell.getDateCellValue().getTime()));
	// }
	//
	// int nameIndex = checkIndexExist(nameIndexMap, INDEX_NAME);
	// if (nameIndex != -1) {
	// Cell cell = row.getCell(nameIndex);
	// this.name = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int nicknameIndex = checkIndexExist(nameIndexMap, INDEX_NICKNAME);
	// if (nicknameIndex != -1) {
	// Cell cell = row.getCell(nicknameIndex);
	// this.nickName = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int emailIndex = checkIndexExist(nameIndexMap, INDEX_EMAIL);
	// if (emailIndex != -1) {
	// Cell cell = row.getCell(emailIndex);
	// this.email = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int phoneIndex = checkIndexExist(nameIndexMap, INDEX_PHONE);
	// if (phoneIndex != -1) {
	// Cell cell = row.getCell(phoneIndex);
	//
	// String phone = StudentExcelUtility.parseCell(cell);
	//
	// String phonePattern1 = "^09\\d{2}-\\d{3}-\\d{3}";
	// String phonePattern2 = "^09\\d{2}-\\d{6}";
	// String phonePattern3 = "^9\\d{8}";
	//
	// Pattern pattern1 = Pattern.compile(phonePattern1);
	// Pattern pattern2 = Pattern.compile(phonePattern2);
	// Pattern pattern3 = Pattern.compile(phonePattern3);
	//
	// if (pattern1.matcher(phone).find()) {
	// this.phone = phone.substring(0, 4) + "-" + phone.substring(5, 8) +
	// phone.substring(9, 12);
	//
	// } else if (pattern2.matcher(phone).find()) {
	// this.phone = phone;
	// } else if (pattern3.matcher(phone).find()) {
	// this.phone = "0" + phone.substring(0, 3) + "-" + phone.substring(3, 9);
	// }
	// }
	//
	// int companyIndex = checkIndexExist(nameIndexMap, INDEX_COMPANY);
	// if (companyIndex != -1) {
	// Cell cell = row.getCell(companyIndex);
	// this.company = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int apartmentIndex = checkIndexExist(nameIndexMap, INDEX_APARTMENT);
	// if (apartmentIndex != -1) {
	// Cell cell = row.getCell(apartmentIndex);
	// this.apartment = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int titleIndex = checkIndexExist(nameIndexMap, INDEX_TITLE);
	// if (titleIndex != -1) {
	// Cell cell = row.getCell(titleIndex);
	// this.title = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int vegeMeatIndex = checkIndexExist(nameIndexMap, INDEX_VEGE_MEAT);
	// if (vegeMeatIndex != -1) {
	// Cell cell = row.getCell(vegeMeatIndex);
	// this.vegeMeat = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int receiptTypeIndex = checkIndexExist(nameIndexMap, INDEX_RECEIPT_TYPE);
	// if (receiptTypeIndex != -1) {
	// Cell cell = row.getCell(receiptTypeIndex);
	// this.receiptType = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int companyNameAndEINIndex = checkIndexExist(nameIndexMap,
	// INDEX_RECEIPT_COMPANY_NAME_AND_EIN);
	// if (companyNameAndEINIndex != -1) {
	// Cell cell = row.getCell(companyNameAndEINIndex);
	// String companyNameAndEIN = StudentExcelUtility.parseCell(cell);
	//
	// if (companyNameAndEIN != "") {
	// String phonePattern = "\\d{8}";
	//
	// Pattern pattern = Pattern.compile(phonePattern);
	// Matcher matcher = pattern.matcher(companyNameAndEIN);
	// if (matcher.find()) {
	// this.receiptCompanyEIN = companyNameAndEIN.substring(matcher.start(),
	// matcher.end());
	//
	// }
	// this.receiptCompanyName = companyNameAndEIN.replaceAll("\\d{8}",
	// "").replaceAll("[^\\u4e00-\\u9fa5]",
	// "");
	//
	// }
	// // this.receiptCompanyName
	// // this.receiptCompanyEIN
	// }
	//
	// int ticketTypeIndex = checkIndexExist(nameIndexMap, INDEX_TICKET_TYPE);
	// if (ticketTypeIndex != -1) {
	// Cell cell = row.getCell(ticketTypeIndex);
	// String ticketType = StudentExcelUtility.parseCell(cell);
	//
	// if (ticketType != "") {
	// String priceReg = "\\d+[,]*\\d+";
	// String ticketTypeReg = ".*[票|券]";
	//
	// Pattern pricePattern = Pattern.compile(priceReg);
	// Pattern ticketPattern = Pattern.compile(ticketTypeReg);
	//
	// Matcher matcher = ticketPattern.matcher(ticketType);
	//
	// if (matcher.find()) {
	// this.ticketType = ticketType.substring(matcher.start(), matcher.end());
	// }
	//
	// Matcher priceMatcher = pricePattern.matcher(ticketType);
	//
	// if (priceMatcher.find()) {
	// this.ticketPrice = ticketType.substring(priceMatcher.start(),
	// priceMatcher.end());
	// }
	// }
	//
	// }
	//
	// int teamMembersIndex = checkIndexExist(nameIndexMap, INDEX_TEAM_MEMBERS);
	// if (teamMembersIndex != -1) {
	// Cell cell = row.getCell(teamMembersIndex);
	// this.teamMembers = StudentExcelUtility.parseCell(cell);
	// }
	//
	// int commentIndex = checkIndexExist(nameIndexMap, INDEX_COMMENT);
	// if (commentIndex != -1) {
	// Cell cell = row.getCell(commentIndex);
	// this.comment = StudentExcelUtility.parseCell(cell);
	// }
	//
	// }

	private static int checkIndexExist(Map<String, Integer> nameIndexMap, String Index) {
		return nameIndexMap.containsKey(Index) ? nameIndexMap.get(Index) : -1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getVegeMeat() {
		return vegeMeat;
	}

	public void setVegeMeat(String vegeMeat) {
		this.vegeMeat = vegeMeat;
	}

	public String getReceiptCompanyName() {
		return receiptCompanyName;
	}

	public void setReceiptCompanyName(String receiptCompanyName) {
		this.receiptCompanyName = receiptCompanyName;
	}

	public String getReceiptCompanyEIN() {
		return receiptCompanyEIN;
	}

	public void setReceiptCompanyEIN(String receiptCompanyEIN) {
		this.receiptCompanyEIN = receiptCompanyEIN;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
