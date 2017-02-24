/**
 * @author Ruben Barros
 * @version 1.0 5/10/2016
 */

package com.sap.requestTypes;

public class QueryParams {
	/**
	 * this variable represents the serviceUrl
	 */
	public final static String SERVICEURL = "https://api5.successfactors.eu/odata/v2/"; 
	/**
	 * this variable represents the post serviceUrl
	 */
	public final static String POSTSERVICEURL = "https://api5.successfactors.eu/odata/v2/upsert"; 
	/**
	 * this variable represents an odata key
	 */
	public final static String PERPERSONAL = "PerPersonal";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPPAYCOMPRECURRING = "EmpPayCompRecurring";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPJOB = "EmpJob";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPEMPLOYMENT = "EmpEmployment";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPCOMPENSATION = "EmpCompensation";
	/**
	 * this variable represents an odata path
	 */
	public final static String PERPERSON = "PerPerson";
	/**
	 * this variable represents an odata path
	 */
	public final static String PEREMAIL = "PerEmail";
	/**
	 * this variable represents an odata path
	 */
	public final static String FREQUENCY = "frequency";
	/**
	 * this variable represents an odata path
	 */
	public final static String DATEOFBIRTH = "dateOfBirth";
	/**
	 * this variable represents an odata key
	 */
	public final static String COUNTRYOFBIRTH = "countryOfBirth";
	/**
	 * this variable represents an odata key
	 */
	public final static String FIRSTNAME = "firstName";
	/**
	 * this variable represents an odata key
	 */
	public final static String LASTNAME = "lastName";
	/**
	 * this variable represents an odata entity
	 */
	public final static String LASTMODIFIEDON = "lastModifiedOn";
	/**
	 * this variable represents an odata path
	 */
	public final static String PERSONNAV = "personNav";
	/**
	 * this variable represents an odata operation
	 */
	public final static String PAYCOMPVALUE = "paycompvalue";
	/**
	 * this variable represents an odata path
	 */
	public final static String COMPANY = "company";
	/**
	 * this variable represents an odata path
	 */
	public final static String LOCATION = "location";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPLOYEECLASS = "employeeClass";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMPLOYMENTTYPE = "employmentType";
	/**
	 * this variable represents an odata path
	 */
	public final static String FTE = "fte";
	/**
	 * this variable represents an odata path
	 */
	public final static String PROBATIONPERIODENDDATE = "probationPeriodEndDate";
	/**
	 * this variable represents an odata path
	 */
	public final static String ISFULLTIMEEMPLOYEE = "isFulltimeEmployee";
	/**
	 * this variable represents an odata path
	 */
	public final static String JOBENTRYDATE = "jobEntryDate";
	/**
	 * this variable represents an odata path
	 */
	public final static String ORIGINALSTARTDATE = "originalStartDate";
	/**
	 * this variable represents an odata path
	 */
	public final static String STARTDATE = "startDate";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMDATE1 = "customDate1";
	/**
	 * this variable represents an odata path
	 */
	public final static String ENDDATE = "endDate";
	/**
	 * this variable represents an odata path
	 */
	public final static String PAYGROUP = "payGroup";
	/**
	 * this variable represents an odata path
	 */
	public final static String PAYCOMPONENT = "payComponent";
	/**
	 * this variable represents an odata path
	 */
	public final static String CURRENCYCODE = "currencyCode";
	/**
	 * this variable represents an odata path
	 */
	public final static String SALUTATION = "Salutation";
	/**
	 * this variable represents an odata path
	 */
	public final static String GENDER = "gender";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMAILTYPE = "emailType";
	/**
	 * this variable represents an odata path
	 */
	public final static String EMAILADDRESS = "emailAddress";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMSTRING1 = "customString1";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMSTRING2 = "customString2";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMSTRING7 = "customString7";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMSTRING31 = "customString31";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMSTRING49 = "customString49";
	/**
	 * this variable represents an odata path
	 */
	public final static String CUSTOMDOUBLE1 = "customDouble1";
	/**
	 * this variable represents an odata path
	 */
	public final static String HOMEADDRESSNAVDEFLT = "homeAddressNavDEFLT";
	/**
	 * this variable represents an odata path
	 */
	public final static String PERSONIDEXTERNAL = "personIdExternal";
	/**
	 * this variable represents an odata path
	 */
	public final static String EXTERNALIDCODE = "personIdExternal%20eq%20";
	/**
	 * this variable represents an odata path
	 */	
	public final static String FROMDATE = "01-01-1990";
	/**
	 * this variable represents an odata path
	 */
	public final static String FILTERPAYCOMP = "%20or%20payComponent%20eq%20%27/TPA_IND%27%20or%20payComponent%20eq%20%2776B1_IND%27%20or%20payComponent%20eq%20%2776C1_IND%27%20or%20payComponent%20eq%20%2776E1_IND%27%20or%20payComponent%20eq%20%2776H1_IND%27%20or%20payComponent%20eq%20%2776CL_IND%27%20or%20payComponent%20eq%20%2776S1_IND%27%20or%20payComponent%20eq%20%27ZTPA_IND%27%20or%20payComponent%20eq%20%274B1F_IND%27%20or%20payComponent%20eq%20%274CLF_IND%27%20or%20payComponent%20eq%20%274450_IND%27%20or%20payComponent%20eq%20%2742CF_IND%27%20or%20payComponent%20eq%20%2742HF_IND%27%20or%20payComponent%20eq%20%2742SF_IND%27%20or%20payComponent%20eq%20%277TPA_IND%27%20or%20payComponent%20eq%20%274TRD_IND%27)";
	/**
	 * this variable represents an odata filter
	 */
	public final static String SSL = "SSL";  
	/**
	 * this variable represents a combination of user and company in format
	 * <userName@companyName>
	 */
	public final static String USER = "SFAPI_SPM@SAPIT3";  
	/**
	 * this variable represents the password
	 */
	public final static String PW = "SAP!2016api"; 
}
