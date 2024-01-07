/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

package au.org.ncallister.alphaess4j.responses;

/**
 *
 */
public enum EmsStatus 
{
    NORMAL("Normal")
    
    ;
    
    public final String apiString;
    
    private EmsStatus(String apiString)
    {
        this.apiString = apiString;
    }
    
    public static EmsStatus byApiString(String apiString)
    {
        for (EmsStatus next : EmsStatus.values())
        {
            if (next.apiString.equals(apiString))
            {
                return next;
            }
        }
        
        throw new IllegalArgumentException("No EmsStatus with API string " + apiString);
    }
}
