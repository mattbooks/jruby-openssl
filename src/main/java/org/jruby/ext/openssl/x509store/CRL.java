/***** BEGIN LICENSE BLOCK *****
 * Version: EPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Eclipse Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/epl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2006 Ola Bini <ola@ologix.com>
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the EPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the EPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jruby.ext.openssl.x509store;

import java.security.cert.X509CRL;

/**
 * c: X509_OBJECT
 *
 * @author <a href="mailto:ola.bini@ki.se">Ola Bini</a>
 */
public class CRL extends X509Object {

    public /* final */ java.security.cert.CRL crl;

    @Deprecated // not-used
    public CRL() { /* */ }

    public CRL(X509CRL crl) {
        this.crl = crl;
    }

    @Override
    public int type() {
        return X509Utils.X509_LU_CRL;
    }

    @Override
    public boolean isName(final Name name) {
        return name.equalTo( ((X509CRL) crl).getIssuerX500Principal() );
    }

    @Override
    public boolean matches(final X509Object other) {
        if (other instanceof CRL) {
            final X509CRL thisCRL = (X509CRL) crl;
            final X509CRL thatCRL = (X509CRL)((CRL) other).crl;
            return thisCRL.getIssuerX500Principal().equals( thatCRL.getIssuerX500Principal() );
        }
        return false;
    }

    @Override
    public int compareTo(final X509Object other) {
        int cmp = super.compareTo(other);
        if (cmp != 0) return cmp;
        return crl.equals( ((CRL) other).crl ) ? 0 : -1;
    }

}// X509_OBJECT_CRL
