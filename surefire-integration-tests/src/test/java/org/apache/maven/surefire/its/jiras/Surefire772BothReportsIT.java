package org.apache.maven.surefire.its.jiras;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.surefire.its.fixture.OutputValidator;
import org.apache.maven.surefire.its.fixture.SurefireIntegrationTestCase;
import org.apache.maven.surefire.its.fixture.SurefireLauncher;
import org.apache.maven.surefire.its.fixture.TestFile;

/**
 * Test Surefire-740 Truncated comma with non us locale
 *
 * @author Kristian Rosenvold
 */
public class Surefire772BothReportsIT
    extends SurefireIntegrationTestCase
{

    public SurefireLauncher unpack()
    {
        return unpack( "/surefire-772-both-reports" ).deleteSiteDir().addGoal( "-Dclean.skip=true" ).failNever();
    }

    public void testReportGeneration()
        throws Exception
    {
        OutputValidator outputValidator =
            unpack().addFailsafeReportOnlyGoal().addSurefireReportOnlyGoal().executeCurrentGoals();

        TestFile siteFile = outputValidator.getSiteFile( "surefire-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertTrue( "Expecting surefire report file", siteFile.isFile() );

        siteFile = outputValidator.getSiteFile( "failsafe-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertTrue( "Expecting failsafe report file", siteFile.isFile() );
    }

    public void testSkippedFailsafeReportGeneration()
        throws Exception
    {
        OutputValidator validator = unpack().
            activateProfile(
                "skipFailsafe" ).addFailsafeReportOnlyGoal().addSurefireReportOnlyGoal().executeCurrentGoals();

        TestFile siteFile = validator.getSiteFile( "surefire-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertTrue( "Expecting surefire report file", siteFile.isFile() );

        siteFile = validator.getSiteFile( "failsafe-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertFalse( "Expecting no failsafe report file", siteFile.isFile() );
    }

    public void testSkippedSurefireReportGeneration()
        throws Exception
    {
        OutputValidator validator = unpack().failNever().
            activateProfile(
                "skipSurefire" ).addFailsafeReportOnlyGoal().addSurefireReportOnlyGoal().executeCurrentGoals();

        TestFile siteFile = validator.getSiteFile( "surefire-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertFalse( "Expecting no surefire report file", siteFile.isFile() );

        siteFile = validator.getSiteFile( "failsafe-report.html" );
        System.out.println( "siteFile.getAbsolutePath() = " + siteFile.getAbsolutePath() );
        assertTrue( "Expecting failsafe report file", siteFile.isFile() );
    }

}
