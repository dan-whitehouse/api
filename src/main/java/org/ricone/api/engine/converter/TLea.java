package org.ricone.api.engine.converter;

import java.util.ArrayList;
import java.util.List;

import org.ricone.api.engine.QLea;
import org.ricone.api.engine.QLeatelephone;
import org.ricone.api.model.Lea;
import org.ricone.api.model.Leatelephone;

import com.querydsl.core.Tuple;

public class TLea 
{
	
	
	private static Lea map(Lea l, Tuple row, QLea ql, QLeatelephone qlt)
	{
		
		return l;
	}
	
	
	public static List<Lea> convertToMulti(List<Tuple> tuples, QLea ql, QLeatelephone qlt)
	{		
		List<Lea> leas = new ArrayList<Lea>();
		
		for (Tuple row : tuples) 
		{	
			Lea l = null;
			if(leas.indexOf(row.get(ql.learefId)) != -1 && leas.get(leas.indexOf(row.get(ql.learefId))) != null)
			{		
				System.out.println("if");
				l = leas.get(leas.indexOf(row.get(ql.learefId)));
				Leatelephone lt = new Leatelephone();
				lt.setLeatelephoneRefId(row.get(qlt.leatelephoneRefId));
				lt.setPrimaryTelephoneNumberIndicator(row.get(qlt.primaryTelephoneNumberIndicator));
				lt.setTelephoneNumber(row.get(qlt.telephoneNumber));
				lt.setTelephoneNumberTypeCode(row.get(qlt.telephoneNumberTypeCode));
				lt.setLea(l);
				
				if(lt.getLeatelephoneRefId() != null)
			    {
			    	l.getLeatelephones().add(lt);
			    } 
			}
			else
			{
				//System.out.println("else");
				l = new Lea();		
				l.setLearefId(row.get(ql.learefId));
				l.setAddressType(row.get(ql.addressType));
				l.setCity(row.get(ql.city));
				l.setCountryCode(row.get(ql.countryCode));
				l.setLeaid(row.get(ql.leaid));
				l.setAddressCountyName(row.get(ql.addressCountyName));
				l.setLeaname(row.get(ql.leaname));
				l.setLeaseaid(row.get(ql.leaseaid));
				l.setLeavendorId(row.get(ql.leavendorId));
				l.setLine2(row.get(ql.line2));
				l.setPostalCode(row.get(ql.postalCode));
				l.setStateCode(row.get(ql.stateCode));
				l.setStreetNumberAndName(row.get(ql.streetNumberAndName));
				
				Leatelephone lt = new Leatelephone();
				lt.setLeatelephoneRefId(row.get(qlt.leatelephoneRefId));
				lt.setPrimaryTelephoneNumberIndicator(row.get(qlt.primaryTelephoneNumberIndicator));
				lt.setTelephoneNumber(row.get(qlt.telephoneNumber));
				lt.setTelephoneNumberTypeCode(row.get(qlt.telephoneNumberTypeCode));
				lt.setLea(l);
				l.getLeatelephones().add(lt);
				
				if(lt.getLeatelephoneRefId() != null)
			    {
			    	l.getLeatelephones().add(lt);
			    } 
			}
			if(l.getLearefId() != null)
		    {
		    	leas.add(l);
		    }			    
		}
		return leas;
	}
	
	public static Lea convertToSingle(List<Tuple> tuples, QLea ql, QLeatelephone qlt)
	{
		Lea l = new Lea();
		for (Tuple row : tuples) 
		{
			l.setLearefId(row.get(ql.learefId));
			l.setAddressType(row.get(ql.addressType));
			l.setCity(row.get(ql.city));
			l.setCountryCode(row.get(ql.countryCode));
			l.setLeaid(row.get(ql.leaid));
			l.setAddressCountyName(row.get(ql.addressCountyName));
			l.setLeaname(row.get(ql.leaname));
			l.setLeaseaid(row.get(ql.leaseaid));
			l.setLeavendorId(row.get(ql.leavendorId));
			l.setLine2(row.get(ql.line2));
			l.setPostalCode(row.get(ql.postalCode));
			l.setStateCode(row.get(ql.stateCode));
			l.setStreetNumberAndName(row.get(ql.streetNumberAndName));
			 
			Leatelephone lt = new Leatelephone();		     
			lt.setLeatelephoneRefId(row.get(qlt.leatelephoneRefId));
			lt.setPrimaryTelephoneNumberIndicator(row.get(qlt.primaryTelephoneNumberIndicator));
			lt.setTelephoneNumber(row.get(qlt.telephoneNumber));
			lt.setTelephoneNumberTypeCode(row.get(qlt.telephoneNumberTypeCode));
			lt.setLea(l);
			
			if(lt.getLeatelephoneRefId() != null)
		     {
		    	 l.getLeatelephones().add(lt);
		     } 
		}
		return l;
	}
}
