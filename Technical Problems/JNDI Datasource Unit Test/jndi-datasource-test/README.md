<h1>The problem of this project</h1>

<p>The main idea of this project was to create Unit tests for a Spring Bean that creates a Datasource based on jndiName.</p>

<h2>SimpleNamingContextBuilder</h2>
<p>After some time, a fast and compact method was found using the SimpleNamingContextBuilder.</p>

<table>
<thead>
  <tr>
    <th colspan="2">SimpleNamingContextBuilder</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><center>Pluses</center></td>
    <td><center>Minuses</center></td>
  </tr>
  <tr>
    <td>Short way</td>
    <td>Deprecated</td>
  </tr>
  <tr>
    <td>Works faster</td>
    <td></td>
  </tr>
</tbody>
</table>

<h2>InitialContext</h2>
<p>I decided to find the second way because the SimpleNamingContextBuilder turned out to be a deprecated method.</p>
<p>For this reason, I found a way with InitialContext.</p>

<table>
<thead>
  <tr>
    <th colspan="2">InitialContext<br></th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><center>Pluses</center></td>
    <td><center>Minuses</center></td>
  </tr>
  <tr>
    <td>Ability to reuse a class</td>
    <td>Creating an additional class</td>
  </tr>
  <tr>
    <td></td>
    <td>Lots of code</td>
  </tr>
  <tr>
    <td></td>
    <td>Tests take longer</td>
  </tr>
</tbody>
</table>

<h2>Simple-JNDI</h2>
<p>Well, the last way, which is, surprisingly, very fast, but it works with in memory datasource, which is installed using the h2 library.</p>

<table>
<thead>
  <tr>
    <th colspan="2">Simple-JNDI<br></th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><center>Pluses</center></td>
    <td><center>Minuses</center></td>
  </tr>
  <tr>
    <td>Very fast</td>
    <td>Need to create additional jndi.properties</td>
  </tr>
  <tr>
    <td></td>
    <td>Lots of code</td>
  </tr>
  <tr>
    <td></td>
    <td>Including the Simple-JNDI library</td>
  </tr>
</tbody>
</table>

<h2>My decision</h2>

<p>What have I chosen for myself? Most likely, this is the first way. This is minor, but it works faster. I don't have a need to reuse InitialContext, so this will become a redundant method.</p>
<p>There are no problems with this class, it just stopped development for the following reason:</p>
<p>Deprecated as of Spring Framework 5.2 in favor of complete solutions from third parties such as Simple-JNDI</p>