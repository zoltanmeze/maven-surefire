package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitPlatformSampleTest
{
    static final String PATTERN = "0123456789ABCDEF";

    static final Logger logger = LoggerFactory.getLogger( JUnitPlatformSampleTest.class );

    @Test
    public void test2ByteCharacterOn1024thPlace()
    {
        StringBuilder stringBuilder = new StringBuilder( 1024 );
        for ( int i = 0; i < 64; i++ )
        {
            stringBuilder.append( PATTERN );
        }
        stringBuilder.replace( 1023, 1024, "\u00FA" );

        logger.info( stringBuilder.toString() );
        Assertions.assertTrue( true );
    }

    @Test
    public void test4ByteCharacterOn1024thPlace()
    {
        StringBuilder stringBuilder = new StringBuilder( 1024 );
        for ( int i = 0; i < 64; i++ )
        {
            stringBuilder.append( PATTERN );
        }
        stringBuilder.replace( 1023, 1024, "\uD83D\uDE35" );

        logger.info( stringBuilder.toString() );
        Assertions.assertTrue( true );
    }
}
