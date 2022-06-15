<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:t24="http://temenos.com/T24KCBAccountOpening" xmlns:acc="http://temenos.com/ACCOUNTKCBCHANNELS">
   <soapenv:Header/>
   <soapenv:Body>
      <t24:KCBAccountCreation>
         <WebRequestCommon>
            <company>KE0010001</company>
            <password>QWERTYui123*</password>
            <userName>COB.01</userName>
         </WebRequestCommon>
         <OfsFunction>
         </OfsFunction>
         <ACCOUNTKCBCHANNELSType id="">
            <acc:CustomerID>${customerNumber}</acc:CustomerID>
            <acc:ProductCode>6004</acc:ProductCode>
            <acc:AccountName1>MWE TESTING</acc:AccountName1>
            <acc:AccountName2>MWE TESTING</acc:AccountName2>
            <acc:ShortName>MWE TESTING</acc:ShortName>
            <acc:Currency>KES</acc:Currency>
            <acc:BranchCode>4001</acc:BranchCode>
            <acc:gPOSTINGRESTRICT g="1">
               <acc:PostingRestrict></acc:PostingRestrict>
            </acc:gPOSTINGRESTRICT>
            <acc:AlternateAcctID></acc:AlternateAcctID>
            <acc:KCBAccountSubSegment>7131</acc:KCBAccountSubSegment>
            <acc:GraduationDate></acc:GraduationDate>
            <acc:GoalAmount></acc:GoalAmount>
            <acc:MobileNumber></acc:MobileNumber>
            <acc:GoalPeriod></acc:GoalPeriod>
            <acc:KRAPin>${kraPin}</acc:KRAPin>
            <acc:DSACode>KECEDSR10397</acc:DSACode>
            <acc:gDSA.SUP g="1">
               <acc:ReferralCode>KECEDSR10397</acc:ReferralCode>
            </acc:gDSA.SUP>
             <acc:GoalName></acc:GoalName>
             <acc:UniqueID></acc:UniqueID>
             <acc:ChannelsUniqueRef>OFF1234EEE30</acc:ChannelsUniqueRef>
             <acc:AutoRenewalOptions></acc:AutoRenewalOptions>
             <acc:gBRANCH.INPUTTER g="1">
                <acc:BranchInputter></acc:BranchInputter>
            </acc:gBRANCH.INPUTTER>
            <acc:gBRANCH.AUTH g="1">
               <acc:BranchAuthorizer></acc:BranchAuthorizer>
            </acc:gBRANCH.AUTH>
            <acc:gAOPC.INPUTTER g="1">
               <acc:AOPCInputter></acc:AOPCInputter>
            </acc:gAOPC.INPUTTER>
            <acc:gAOPC.AUTHORISER g="1">
               <acc:AOPCAuthoriser></acc:AOPCAuthoriser>
            </acc:gAOPC.AUTHORISER>
         </ACCOUNTKCBCHANNELSType>
      </t24:KCBAccountCreation>
   </soapenv:Body>
</soapenv:Envelope>
