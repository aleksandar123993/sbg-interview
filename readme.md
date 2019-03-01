## Seven Bridges Genomics Interview task

###author: Aleksandar Ivanisevic

#### db create:

- `CREATE USER cgccli PASSWORD 'password';`
- `CREATE DATABASE cgccli;`
- `GRANT ALL PRIVILEGES ON DATABASE cgccli TO cgccli;`

#### For tests:

- `CREATE USER cgccli PASSWORD 'password';`
- `CREATE DATABASE cgccli_test;`
- `GRANT ALL PRIVILEGES ON DATABASE cgccli_test TO cgccli;`

#### Description:

- CLI tool to support CGC Api. 
- Each command must start with `cgccli`
- supported `--help` command
- supported `--history` command
- available commands: 
`projects list, files list, files stat, files update, files download`