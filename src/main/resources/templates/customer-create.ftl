<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:t24="http://temenos.com/T24KCBChannelService" xmlns:cus="http://temenos.com/CUSTOMERKCBCHANNELS">
   <soapenv:Header/>
   <soapenv:Body>
      <t24:T24WebServicesImplService>
         <WebRequestCommon>
            <company>KE0010001</company>
            <password>QWERTYui123*</password>
            <userName>COB.01</userName>
         </WebRequestCommon>
         <OfsFunction>
         </OfsFunction>
         <CUSTOMERKCBCHANNELSType id="">
              <cus:Mnemonic>ID336112T</cus:Mnemonic>
            <cus:gSHORTNAME g="1">
               <cus:ShortName>${firstName}</cus:ShortName>
            </cus:gSHORTNAME>
            <cus:gNAME1 g="1">
               <cus:Name>${fullName}</cus:Name>
            </cus:gNAME1>
            <cus:gSTREET g="1">
               <cus:Street>${street}</cus:Street>
            </cus:gSTREET>
            <cus:gTOWNCOUNTRY g="1">
               <cus:TownCountry>$(townCountry)</cus:TownCountry>
            </cus:gTOWNCOUNTRY>
            <cus:Sector>1001</cus:Sector>
            <cus:BranchCode>4001</cus:BranchCode>
            <cus:Industry>1699</cus:Industry>
            <cus:Target>11</cus:Target>
            <cus:Nationality>KE</cus:Nationality>
            <cus:CustomerStatus>1</cus:CustomerStatus>
            <cus:Residence>KE</cus:Residence>
            <cus:gLEGALID g="1">
               <cus:mLEGALID m="1">
                  <cus:LegalID>${customerIdNumber}</cus:LegalID>
                  <cus:LegalDocName>ID.KE</cus:LegalDocName>
                  <cus:LegalHolderName>${fullName}</cus:LegalHolderName>
                  <cus:LegalIssueAuth>GOK</cus:LegalIssueAuth>
                  <cus:LegalIssueDate>20170202</cus:LegalIssueDate>
                  <cus:LegalExpiryDate></cus:LegalExpiryDate>
               </cus:mLEGALID>
            </cus:gLEGALID>
            <cus:Language>1</cus:Language>
            <cus:gPHONE1 g="1">
               <cus:mPHONE1 m="1">
                  <cus:PhoneNumber>${phoneNumber}</cus:PhoneNumber>
                  <cus:Email>${email}</cus:Email>
               </cus:mPHONE1>
            </cus:gPHONE1>
            <cus:KCBSector>1001</cus:KCBSector>
            <cus:CustomerSegment>8100</cus:CustomerSegment>
            <cus:gKCB.DOC.NAME g="1">
               <cus:mKCB.DOC.NAME m="1">
                  <cus:KCBDocName>NATIONAL.ID</cus:KCBDocName>
                  <cus:KCBDocID>{customerIdNumber}</cus:KCBDocID>
                  <cus:DocumentIssueAuthority>GOK</cus:DocumentIssueAuthority>
                  <cus:DocumentIssueDate>20170202</cus:DocumentIssueDate>
                  <cus:DocumentExpiryDate></cus:DocumentExpiryDate>
               </cus:mKCB.DOC.NAME>
            </cus:gKCB.DOC.NAME>
         </CUSTOMERKCBCHANNELSType>
      </t24:KCBCustomerCreation>
   </soapenv:Body>
</soapenv:Envelope>